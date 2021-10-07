package eu.arrowhead.client.skeleton.provider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public
class PlugSwitcher {
    public static double readSetPoint(){
        try{


            String deviceId = "24";
            ProcessBuilder pb = new ProcessBuilder("py ","C:\\Users\\javsal\\Documents\\GitHub\\zwave\\backup-212021\\demo-plug\\demo-plug-provider\\src\\main\\java\\ai\\aitia\\demo\\thermo_provider\\SwitchPlug.py",""+deviceId/*,""+number2*/);
            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String setPoint = in.readLine();
            double setPointValue= Double.parseDouble(setPoint);
            System.out.println("SetPoint Value is : "+setPointValue);
            return setPointValue;

        }catch(Exception e){System.out.println(e);}


        return 0;
    }

    public static void switchPlug(double switchWishedState){
        try{


            String deviceId = "25";
            /*String switchPlug = "0";
            if (switchWishedState == 255) {
                 switchPlug = "255";
            }

            else if (switchWishedState == 0) {
                 switchPlug = "0";
            }*/

            String switchWishedState1 = Double.toString(switchWishedState);
            ProcessBuilder pb = new ProcessBuilder("py ","C:\\Users\\javsal\\Documents\\GitHub\\Arrowhead_Zwave_System\\demo-plug\\demo-plug-provider\\src\\main\\java\\ai\\aitia\\demo\\thermo_provider\\set_thermostat_setpoint.py",""+switchWishedState1);

            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String response = in.readLine();
            double responseValue= Double.parseDouble(response);
            System.out.println("Plug Response is : "+responseValue);

        }catch(Exception e){System.out.println(e);}


    }



}