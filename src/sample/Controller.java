package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigInteger;

public class Controller  {

    @FXML
    private TextField bitTextArea;
    @FXML
    private TextArea nTextArea;
    @FXML
    private TextArea eTextArea;
    @FXML
    private TextArea dTextArea;
    @FXML
    private TextArea eMessageText;
    @FXML
    private TextArea eEncryptedMessage;
    @FXML
    private TextArea dEncryptedMessage;
    @FXML
    private TextArea dDecryptedMessage;
    Model model ;
    BigInteger[] eachSymbolCode;
    BigInteger symbolDivider;
    BigInteger wordDivider;

//    public Controller(Model model){
//        this.model = model;
//    }
    public void generateKey(){
        model = new Model();
        model.bit = Integer.parseInt(bitTextArea.getText());
        model.generateP();
        model.generateQ();

        model.calcN();
        model.calcFEuler();
        model.generateE();
        model.calcD();
        nTextArea.setText(model.n + "");
        eTextArea.setText(model.e + "");
        dTextArea.setText(model.getD() + "");
        System.out.println(model.bit);
    }
    public void encrypt(){
        char[] messageArray = eMessageText.getText().toCharArray();
        eachSymbolCode = new BigInteger[messageArray.length];
        String encryptedMessage = new String("");
        symbolDivider = new BigInteger(model.crypt('/') + "");
        wordDivider = new BigInteger(model.crypt(' ') + "");
        for (int i = 0; i < messageArray.length; i++){
            //if (messageArray[i]==' ') continue;
            encryptedMessage +=  model.crypt(messageArray[i]) + " ";
        }
        eEncryptedMessage.setText(encryptedMessage);
    }
    public void decrypt(){
        String decrypted = new String("");
//        String[] words = dEncryptedMessage.getText().split(" ");
//        for (int i = 0; i < words.length; i++){
//            String[] symbols = words[i].split(symbolDivider + "");
//            for (int j = 0; j<symbols.length; j++){
//                decrypted = decrypted + (char)(model.decrypt(new BigInteger(symbols[j])).intValue());
//            }
//            decrypted +=" ";
//        }
        String[] symbolArray = dEncryptedMessage.getText().split(" ");
        for (int i = 0; i < symbolArray.length; i++){
            decrypted += (char) (model.decrypt(new BigInteger(symbolArray[i])).intValue() );
        }
        dDecryptedMessage.setText(decrypted);
    }
}
