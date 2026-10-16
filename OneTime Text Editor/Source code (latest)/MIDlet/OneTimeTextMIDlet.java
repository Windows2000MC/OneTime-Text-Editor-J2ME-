
//OneTime Text MIDlet
//Version 1.5
//By Tanvir Islam
//Windows2000MC

package MIDlet;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;
import java.io.*;
import javax.microedition.io.*;


public class OneTimeTextMIDlet extends MIDlet implements CommandListener, Runnable {

private Display d;
private Form Main, TextRMS, Settings, DeviceInfo, HTTP;
private Form Exit;
private StringItem HTTPResult, HTTPStatus, DeviceNameCustomData;
private List Menu, TextRMSOptions;
private ChoiceGroup HTTPPopup, FileManager;
private TextField TextEdit,DeviceName,HTTPAddress;
private Command cmdExit, cmdBack, cmdOK, cmdEdit, cmdSave, cmdLoad;
private Command cmdOK1, cmdOK2, cmdOK3, cmdOK4, cmdOK5, cmdBack1, cmdBack2, cmdBack3, cmdBack4, cmdBack5, cmdBack6, cmdBack7, cmdBack8, cmdBack9, cmdBack10, cmdBack11, cmdTest, cmdOp, cmdOp1, cmdDelete, cmdSave1, cmdFetch;


private static final String TextRMSData="OneTimeTextRMSData";
private static final String SettingsData="OneTimeTextSettings";
private static final String Clear="";
private String URLString="http://www.google.com/";
private String DeviceNameCustom="";


//Public live screens and commands

public OneTimeTextMIDlet() {
d=Display.getDisplay(this);

cmdExit=new Command("Exit",Command.EXIT,2);
cmdBack=new Command("Back",Command.OK,2);
cmdBack1=new Command("Back",Command.OK,2);
cmdBack2=new Command("Back",Command.OK,2);
cmdBack3=new Command("Back",Command.OK,2);
cmdBack4=new Command("Discard",Command.OK,2);
cmdBack5=new Command("Back",Command.OK,2);
cmdBack6=new Command("Back",Command.OK,2);
cmdBack7=new Command("Back",Command.OK,2);
cmdBack8=new Command("Back",Command.OK,2);
cmdBack9=new Command("No",Command.OK,2);
cmdBack10=new Command("Back",Command.OK,2);
cmdBack11=new Command("Back",Command.OK,2);
cmdOK=new Command("Ok",Command.OK,1);
cmdOK1=new Command("Ok",Command.OK,1);
cmdOK2=new Command("Ok",Command.OK,1);
cmdOK3=new Command("Save Changes",Command.OK,1);
cmdOK4=new Command("Ok",Command.OK,1);
cmdOK5=new Command("Yes",Command.OK,1);
cmdEdit=new Command("Edit",Command.OK,1);
cmdSave=new Command("Save",Command.OK,1);
cmdSave1=new Command("Save",Command.OK,1);
cmdLoad=new Command("Load",Command.OK,1);
cmdTest=new Command("More info",Command.OK,1);
cmdOp=new Command("Options",Command.OK,1);
cmdOp1=new Command("Options",Command.OK,1);
cmdDelete=new Command("Delete",Command.OK,1);
cmdFetch=new Command("Fetch Data",Command.OK,1);

Main=new Form("References");
TextRMS=new Form("Text in RMS");
Settings=new Form("Settings");
HTTP=new Form("HTTP Response");
DeviceInfo=new Form("Device Info");
Exit=new Form("");
TextEdit=new TextField("Edit Text","",10240,TextField.ANY);
DeviceName=new TextField("Device Name",DeviceNameCustom,128,TextField.ANY);
HTTPAddress=new TextField("Enter URL",URLString,2048,TextField.ANY);
HTTPResult=new StringItem("Connection : ","");
HTTPStatus=new StringItem("Status : ","");
DeviceNameCustomData=new StringItem("Device Name : ","");

Exit.append("Do you want to exit this application?");
Exit.addCommand(cmdOK1);
Exit.addCommand(cmdBack);
Exit.setCommandListener(this);

HTTP.append(HTTPAddress);
HTTP.append(HTTPResult);
HTTP.append(HTTPStatus);
HTTP.addCommand(cmdFetch);
HTTP.addCommand(cmdBack10);
HTTP.setCommandListener(this);

HTTPPopup = new ChoiceGroup("Show HTTP Pop-up message :",ChoiceGroup.EXCLUSIVE);
HTTPPopup.append("Enable",null);
HTTPPopup.append("Disable",null);
HTTPPopup.setSelectedIndex(0,true);

FileManager = new ChoiceGroup("Use Beta Features : ",ChoiceGroup.EXCLUSIVE);
FileManager.append("Enable",null);
FileManager.append("Disable",null);
FileManager.setSelectedIndex(1,true);

Settings.append(DeviceName);
Settings.append("Device name shows on 'Device info' option and can be modified (reqires a application restart)");
Settings.append(HTTPPopup);
Settings.append("Enable or Disable for the message pop-up when clicking 'HTTP Response' option");
Settings.append(FileManager);
Settings.append("Use Beta features that are halfy-made or experiment purpose");
Settings.addCommand(cmdSave1);
Settings.addCommand(cmdBack8);
Settings.setCommandListener(this);

TextRMSOptions=new List("Text RMS Options",List.IMPLICIT);
TextRMSOptions.append("Save text",null);
TextRMSOptions.append("Load text",null);
TextRMSOptions.append("Clear text",null);
TextRMSOptions.append("Delete text",null);
TextRMSOptions.addCommand(cmdOK2);
TextRMSOptions.addCommand(cmdBack1);
TextRMSOptions.setCommandListener(this);

TextRMS.append(TextEdit);
TextRMS.addCommand(cmdBack2);
TextRMS.addCommand(cmdOp);
TextRMS.setCommandListener(this);

Menu=new List("Menu",List.IMPLICIT);
Menu.append("Text in RMS",null);
Menu.append("HTTP Response",null);
Menu.append("Device Info",null);
Menu.append("Settings",null);
Menu.append("Help",null);
Menu.append("About",null);
Menu.addCommand(cmdOK);
Menu.addCommand(cmdExit);
Menu.setCommandListener(this);

Main.append("OneTime Text Editor is comparatively a new idea in J2ME Community. In coding this application, I have gathered some ideas from different online and offline sources and i'm also grateful to suggestions offered by our regular viewers from YouTube and our esteemed J2ME Programmers in Bangladesh and other countries. In particular, i would like to acknowledge that i have gathered ideas, tutorials, working with other applications from the following sources -");
Main.append("\nJ2ME SDK Mobile - devloping entire source code and application\n\nPaintCAD J2ME - Making application icon and other icons\n\nJ2ME IDE by DASH ANIMATION V2 - RMS example codeblock\n\nInternet - The basic coding skills in J2ME\n\nGoogle Gemini - Adding image to form ui and creating the code for 'Device Info' option\n\nnnproject - collecting api location from j2me api tester v0.7.11 (via viewing raw complied class file)\n\nEnglish Grammar and Composition (Class 9 and 10) Published by NCTB, Bangladesh - Proper sentence making and spelling");
Main.addCommand(cmdBack);
Main.setCommandListener(this);

functionLoadDeviceName();
DeviceNameCustomData.setText(DeviceName.getString());
DeviceInfo.append(DeviceNameCustomData);
DeviceInfo.append("\nJ2ME Runtime\n");
appendProperty("MicroEdition Configuration", "microedition.configuration");
appendProperty("MIDP Profile", "microedition.profiles");
appendProperty("Platform", "microedition.platform");
DeviceInfo.append("\nJ2ME API\n");
checkApi("JSR-75 FileConnection", "javax.microedition.io.Connector", "file.conn.version");
checkApi("JSR-82 Bluetooth", "javax.bluetooth.LocalDevice", "bluetooth.api.version");
checkApi("JSR-135 MMAPI", "javax.microedition.media.Manager", "supports.audio.capture");
checkApi("JSR-184 Mobile 3D Graphics", "javax.microedition.m3g.Graphics3D", null);
checkApi("JSR-120/JSR-205 Wireless Messaging", "javax.wireless.messaging.Message", "javax.wireless.messaging.version");
DeviceInfo.addCommand(cmdBack7);
DeviceInfo.setCommandListener(this);
}

protected void startApp() {
Alert a=new Alert("","OneTime Text Editor\nVersion - 1.5\nTanvir Islam",null,AlertType.INFO);
a.setTimeout(2500);
d.setCurrent(a,Menu);
}


protected void pauseApp() {}
protected void destroyApp(boolean unconditional) {}


//Application's core (screens, alerts, funtions and logic)

public void run() {
HttpConnection hc = null;
InputStream    is = null;
String URL=URLString;
try {
hc = (HttpConnection)Connector.open(URL);
hc.setRequestMethod(HttpConnection.GET);
int code =hc.getResponseCode();
is = hc.openInputStream();
byte[] buf = new byte[256];
int n = is.read(buf);
if (n > 0)
HTTPResult.setText("HTTP "+code+"\n"+new String(buf,0,n));
if (code==404)
HTTPStatus.setText("Server not found!");
if (code==302)
HTTPStatus.setText("Connection were redirected to other server or No data plan was activated!");
if (code==403)
HTTPStatus.setText("Connection for this server were no longer accessible!");
if (code==200)
HTTPStatus.setText("Connected! The server is active and running!");
if (code==500)
HTTPStatus.setText("Server Error!");
} catch (Exception e) {
HTTPResult.setText("Error : "+e.getMessage());
} finally {
try{if(is!=null)
is.close();}
catch(Exception ig){}
try{if(hc!=null)
hc.close();}
catch(Exception ig){}
}
}

private void screenHTTPNotes() {
Form HTTPNotes=new Form("");
try {
functionLoadDeviceName();
int currentIndex=HTTPPopup.getSelectedIndex();
if (currentIndex==1) {
d.setCurrent(HTTP);
}
if(currentIndex==0) {
HTTPNotes.append("This option reqires Internet Connection and it may charges your bill (also includes VAT) for Data from your Carrier on phone or SIM card.\n\nOther Connections such as WI-FI (WLAN), Hotspot, Data connection over Bluetooth can be accpectble.\n\nContinue?");
HTTPNotes.addCommand(cmdOK5);
HTTPNotes.addCommand(cmdBack9);
HTTPNotes.setCommandListener(this);
d.setCurrent(HTTPNotes);
}
} catch (Exception e) { }
}

private void screenHTTP() {
d.setCurrent(HTTP);
}

private void functionalTextRMSOptions(int index1) {
switch (index1) {
case 0:
functionSaveTextRMS();
alertSavedTextRMS();
break;
case 1:
functionLoadTextRMS();
alertLoadedTextRMS();
break;
case 2:
functionClearTextRMS();
break;
case 3:
screenDeleteRMS();
break;
default:
break;
}
}

private void functionalMenu(int index) {
switch (index) {
case 0:
showScreenTextRMS();
break;
case 1:
screenHTTPNotes();
break;
case 2:
functionLoadDeviceName();
screenDeviceInfo();
break;
case 3:
showScreenSettings();
break;
case 4:
screenHelp();
break;
case 5:
screenAbout();
break;
default:
break;
}
}

private void showScreenTextRMS() {
screenTextRMS();
}

private void screenTextRMSOptions() {
d.setCurrent(TextRMSOptions);
}

private void showScreenSettings() {
screenSettings();
}

private void screenTextRMS() {
d.setCurrent(TextRMS);
}

private void screenSettings() {
functionLoadDeviceName();
d.setCurrent(Settings);
}

private void screenBackSaveRMS() {
Form DoSaveRMS=new Form("");
DoSaveRMS.append("You clicked 'Back', but didn't save your text. Do you want to save changes?");
DoSaveRMS.addCommand(cmdOK3);
DoSaveRMS.addCommand(cmdBack4);
DoSaveRMS.setCommandListener(this);
d.setCurrent(DoSaveRMS);
}

private void screenDeleteRMS() {
Form DeleteRMS=new Form("");
DeleteRMS.append("Do you want to erase your text data forever?");
DeleteRMS.addCommand(cmdDelete);
DeleteRMS.addCommand(cmdBack3);
DeleteRMS.setCommandListener(this);
d.setCurrent(DeleteRMS);
}

private void functionBackSaveTextRMS() {
functionSaveTextRMS();
Alert BackSaveRMSData=new Alert("","Your text has been saved in your device storage!",null,AlertType.INFO);
BackSaveRMSData.setTimeout(2000);
d.setCurrent(BackSaveRMSData,Menu);
}

private void functionClearTextRMS() {
TextEdit.setString(Clear);
Alert ClearRMS=new Alert("","Your text is cleared from TextField!",null,AlertType.INFO);
ClearRMS.setTimeout(2000);
d.setCurrent(ClearRMS,TextRMS);
}

private void alertDiscardTextRMS() {
TextEdit.setString(Clear);
Alert DiscardRMS=new Alert("","Your text has been discarded!",null,AlertType.INFO);
DiscardRMS.setTimeout(1000);
d.setCurrent(DiscardRMS,Menu);
}

private void alertDeleteTextRMS() {
TextEdit.setString(Clear);
functionSaveTextRMS();
Alert DeleteRMSData=new Alert("","Your text has been erased from your device storage!",null,AlertType.INFO);
DeleteRMSData.setTimeout(2000);
d.setCurrent(DeleteRMSData,TextRMS);
}

private void alertSavedTextRMS() {
Alert SavedRMS=new Alert("","Your text has been saved in your device storage!",null,AlertType.INFO);
SavedRMS.setTimeout(2000);
d.setCurrent(SavedRMS,TextRMS);
}

private void alertLoadedTextRMS() {
Alert LoadedRMS=new Alert("","Your text has been loaded from your device storage!",null,AlertType.INFO);
LoadedRMS.setTimeout(2000);
d.setCurrent(LoadedRMS,TextRMS);
}

private void alertSavedSettingsConfig() {
functionSaveDeviceName();
Alert SavedSettingsCofig=new Alert("","Your settings configuration has been saved!",null,AlertType.INFO);
SavedSettingsCofig.setTimeout(2000);
d.setCurrent(SavedSettingsCofig,Menu);
}

private void functionLoadTextRMS() {
RecordStore rs = null;
try {
rs = RecordStore.openRecordStore(TextRMSData,false);
if(rs.getNumRecords()>0)
TextEdit.setString(new String(rs.getRecord(1)));
} catch(Exception e){}
finally {
try{if(rs!=null)
rs.closeRecordStore();}
catch(Exception ig){}
}
}

private void functionSaveTextRMS() {
RecordStore rs = null;
try {
rs = RecordStore.openRecordStore(TextRMSData,true);
byte[] b =TextEdit.getString().getBytes();
if(rs.getNumRecords()==0)
rs.addRecord(b,0,b.length);
else rs.setRecord(1,b,0,b.length);
} catch(Exception e){}
finally {
try{if(rs!=null)
rs.closeRecordStore();}
catch(Exception ig){}
}
}

private void functionLoadDeviceName() {
RecordStore rs=null;
try {
rs=RecordStore.openRecordStore(SettingsData,false);
if(rs.getNumRecords()>0){
String v=new String(rs.getRecord(1));
int p1=v.indexOf('|');
int p2=v.indexOf('|',p1+1);
if(p1!=-1){
DeviceName.setString(v.substring(0,p1));
if(p2!=-1){
HTTPPopup.setSelectedIndex(Integer.parseInt(v.substring(p1+1,p2)),true);
FileManager.setSelectedIndex(Integer.parseInt(v.substring(p2+1)),true);
}
}
}
} catch(Exception e){}
finally{
try{if(rs!=null)
rs.closeRecordStore();}
catch(Exception ig){}
}
}

private void functionSaveDeviceName() {
RecordStore rs=null;
try {
rs=RecordStore.openRecordStore(SettingsData,true);
String v=DeviceName.getString()+"|"+HTTPPopup.getSelectedIndex()+"|"+FileManager.getSelectedIndex();
byte[] b=v.getBytes();
if(rs.getNumRecords()==0)
rs.addRecord(b,0,b.length);
else
rs.setRecord(1,b,0,b.length);
} catch(Exception e){}
finally{
try{if(rs!=null)
rs.closeRecordStore();}
catch(Exception ig){}
}
}

private void screenUnknown() {
d.setCurrent(Main);
}

private void alertNextUpdate() {
Alert NextUpdate=new Alert("","This feature will be added on future updates!",null,AlertType.INFO);
NextUpdate.setTimeout(2000);
d.setCurrent(NextUpdate,Menu);
}

private void screenAbout() {
Form About=new Form("About");
try {
Image MIDleticon = Image.createImage("/Resources/MIDlet/MIDleticon.png");
Image Win2000MC = Image.createImage("/Resources/Windows2000MC.png");
Image Dash = Image.createImage("/Resources/DASH_ANIMATION_V2.png");
ImageItem MIDleticonitem = new ImageItem("OneTime Text Editor",MIDleticon,ImageItem.LAYOUT_CENTER,"Error 121");
ImageItem Win2000MCitem = new ImageItem("YouTube - Windows2000MC",Win2000MC,ImageItem.LAYOUT_LEFT,"Error 121");
ImageItem DASHitem = new ImageItem("DASH ANIMATION V2",Dash,ImageItem.LAYOUT_LEFT,"Error 121");
About.append(MIDleticonitem);
About.append("Version - 1.5\nBy Tanvir Islam\n\nWritten and Compiled on J2ME SDK Mobile on Symphony D54+ !\nMade in Bangladesh\n");
About.append("\nThanks to :\n");
About.append(DASHitem);
About.append("For making J2ME IDE with Built-In Sinppets for easier coding!");
About.append(Win2000MCitem);
About.append("https://www.youtube.com/@windows2000_mc\n\nWindows2000MC Website\nhttps://sites.google.com/view/windows2000mc-bangladesh/home");
About.append("\nTo prevent getting errors on Nokia S60, S40 and Sony Ericsson java phones, please set the storage permission to Always for future updates...");
About.append("\n\nWhat's new\n\n1. Code was rewritten\n2. Added MIDlet icon\n3. Added Proper Menu System\n4. Added ability to take full control over text in RMS\n5. Added Device Info\n6. Added fully functional Settings\n7. Added 'HTTP Response'");
About.append("\n\nCompletely written inside the MIDlet, no extra fucking classes to mess because i have zero skills of advanced coding...");
} catch (IOException e) {
About.append("Error 128");
e.printStackTrace();
}
About.addCommand(cmdBack);
About.addCommand(cmdTest);
About.setCommandListener(this);
d.setCurrent(About);
}

private void screenHelp() {
Form Help=new Form("Help");
try {
Image TextRMSicon = Image.createImage("/Resources/TextRMS.png");
Image DeviceInfoicon = Image.createImage("/Resources/Hardware.png");
Image Networkicon = Image.createImage("/Resources/Network.png");
ImageItem TextRMSitem = new ImageItem("Text in RMS",TextRMSicon,ImageItem.LAYOUT_LEFT,"Error 121");
ImageItem DeviceInfoitem = new ImageItem("Device Info",DeviceInfoicon,ImageItem.LAYOUT_LEFT,"Error 121");
ImageItem Networkitem = new ImageItem("HTTP Response",Networkicon,ImageItem.LAYOUT_LEFT,"Error 121");
Help.append("OneTime Text Editor is a tool that you can write a text and save in RMS and it's easy!");
Help.append(TextRMSitem);
Help.append("This primary option brings to you to the text field where you want to write and edit text in this field. If you click the right soft key or click 'Options' via touch screen, it will bring the options which you can take full control over your text!\n\nSave Text -> saves text in RMS Database\nLoad Text -> loads text from RMS Database\nClear Text -> clears text from text field\nDelete text -> clears text and saves in RMS Database");
Help.append(DeviceInfoitem);
Help.append("This option shows your device's java api and version. Also you can see your device's name which can be modified via 'Settings' option\n");
Help.append(Networkitem);
Help.append("This option uses Internet Connection to work. Enter the URL and click 'Fetch Data' to check the server's status and HTTP code. The response time may differ on different connection usage. Also if you want to stop the annoying message pop-up everytime you click this 'HTTP Response', go to 'Settings' and disable 'Show HTTP Pop-up message'\n");
} catch (IOException e) {
Help.append("Error 128");
e.printStackTrace();
}
Help.addCommand(cmdBack);
Help.setCommandListener(this);
d.setCurrent(Help);
}

private void appendProperty(String label, String propName) {
String value = System.getProperty(propName);
DeviceInfo.append(new StringItem(label + ": ", value != null ? value : "Not Available"));
}

private void checkApi(String apiName, String className, String versionProp) {
boolean supported = false;
String version = "Supported";
try {
Class.forName(className);
supported = true;
if (versionProp != null) {
String ver = System.getProperty(versionProp);
if (ver != null) {
version = ver;
}
}
} catch (ClassNotFoundException e) {
supported = false;
} catch (IOException e) {
supported = false;
}
String status = supported ? version : "Not Supported";
DeviceInfo.append(new StringItem(apiName + ": ", status));
}

private void screenDeviceInfo() {
functionLoadDeviceName();
d.setCurrent(DeviceInfo);
}

private void commandBack() {
d.setCurrent(Menu);
}

private void exitCom() {
d.setCurrent(Exit);
}

private void exitApp() {
destroyApp(false);
notifyDestroyed();
}

//Global Multi-Screen Navigator and Command Handler

public void commandAction(Command c, Displayable d) {
if (c==cmdExit) {
exitCom();
}
if (c==cmdOK1) {
exitApp();
}
if (c==cmdBack) {
commandBack();
}
if (c==cmdOK) {
int selectedIndex=Menu.getSelectedIndex();
functionalMenu(selectedIndex); 
}
if (c==cmdTest) {
screenUnknown();
}
if (c==cmdOp) {
screenTextRMSOptions();
}
if (c==cmdBack1) {
screenTextRMS();
}
if (c==cmdOK2) {
int selectedIndex1=TextRMSOptions.getSelectedIndex();
functionalTextRMSOptions(selectedIndex1);
}
if (c==cmdBack2) {
screenBackSaveRMS();
}
if (c==cmdBack3) {
screenTextRMS(); 
}
if (c==cmdDelete) {
alertDeleteTextRMS();
}
if (c==cmdBack4) {
alertDiscardTextRMS();
}
if (c==cmdOK3) {
functionBackSaveTextRMS();
}
if (c==cmdBack5) {
commandBack();
}
if (c==cmdOK4) {
}
if (c==cmdBack6) {
}
if (c==cmdBack7) {
commandBack();
}
if (c==cmdBack8) {
commandBack();
}
if (c==cmdBack9) {
commandBack();
}
if (c==cmdBack10) {
commandBack();
}
if (c==cmdBack11) {
commandBack();
}
if (c==cmdSave1) {
functionSaveDeviceName();
alertSavedSettingsConfig();
}
if (c==cmdOK5) {
screenHTTP();
}
if (c==cmdFetch) {
HTTPResult.setText("Connecting...");
HTTPStatus.setText("Preparing the connection first...");
new Thread(this).start();
}
}
}





















































































































































