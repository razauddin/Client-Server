import java.io.BufferedReader;

import javax.swing.JTextArea;

public class ThreadRead implements Runnable {
    JTextArea area;
    BufferedReader in;

    ThreadRead(JTextArea area, BufferedReader in) {
        this.area = area;
        this.in = in;
    }

    public void run() {
        try {
        String receive = "";
        while ((receive = in.readLine()) != null) {
            area.append(receive + "\n");
        }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
