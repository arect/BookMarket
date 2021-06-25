package biz;

import dao.GetData;
import entity.MyMessage;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test")
})
public class PrintBean implements MessageListener {
    @Resource
    private MessageDrivenContext mdc;
    @PersistenceContext(unitName = "simpleJPA")
    private EntityManager m;
    public void onMessage(Message inMessage) {
        TextMessage msg;
        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;

                String[] info = msg.getText().split(";");
                if (info.length != 2) {
                    return;
                }
                GetData gd = new GetData(m);
                String orderText = "";
                if (info[0].equals("#ALL#")){
                    orderText = gd.GetAllOrders();
                }
                else {
                    orderText = gd.GetOrders(info[0]);
                }
                File file = new File(info[1]);
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                orderText = "{ \"orders\": " + orderText + "}";
                bufferWriter.write(orderText);
                bufferWriter.flush();
                bufferWriter.close();
                fileWriter.close();
                MyMessage.setMsg("finished");
            }else {
                System.out.println("[error]: " + inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}
