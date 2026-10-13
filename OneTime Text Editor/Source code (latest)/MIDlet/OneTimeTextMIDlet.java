
//OneTime Text MIDlet
//Version 1.4
//By Tanvir Islam
//Windows2000MC

package MIDlet;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;
import javax.microedition.media.*;
import java.io.*;
import javax.microedition.io.*;
import javax.microedition.io.file.*;

public class OneTimeTextMIDlet extends MIDlet implements CommandListener {

private Display d;
private Form Main, TextRMS, TextJSR75, Settings, DeviceInfo;
private Form Exit;
private List Menu, TextRMSOptions, TextJSR75Options;
private TextField TextEdit, TextEditJSR75,FilePath,FileName,DeviceName;
private Command cmdExit, cmdBack, cmdOK, cmdEdit, cmdSave, cmdLoad;
private Command cmdOK1, cmdOK2, cmdOK3, cmdOK4, cmdBack1, cmdBack2, cmdBack3, cmdBack4, cmdBack5, cmdBack6, cmdBack7, cmdBack8, cmdTest, cmdOp, cmdOp1, cmdDelete, cmdSave1;

private static final String TextRMSData="OneTimeTextRMSData";
private static final String DeviceNameData="OneTimeTextSettingsDeviceNameData";
private static final String Clear="";
private static final String FilePathURL="file:///";
private static final String FileNameData="";
private static final String FilePathLocationURL="";
private static final String FileFormat=".txt";
private static final String LoadTextJSR75="";

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
cmdOK=new Command("Ok",Command.OK,1);
cmdOK1=new Command("Ok",Command.OK,1);
cmdOK2=new Command("Ok",Command.OK,1);
cmdOK3=new Command("Save Changes",Command.OK,1);
cmdOK4=new Command("Ok",Command.OK,1);
cmdEdit=new Command("Edit",Command.OK,1);
cmdSave=new Command("Save",Command.OK,1);
cmdSave1=new Command("Save",Command.OK,1);
cmdLoad=new Command("Load",Command.OK,1);
cmdTest=new Command("Horonable Mention",Command.OK,1);
cmdOp=new Command("Options",Command.OK,1);
cmdOp1=new Command("Options",Command.OK,1);
cmdDelete=new Command("Delete",Command.OK,1);

Main=new Form("Honorable Mention");
TextRMS=new Form("Text in RMS");
TextJSR75=new Form("Text in File System");
Settings=new Form("Settings");
DeviceInfo=new Form("Device Info");
Exit=new Form("");
TextEdit=new TextField("Edit Text","",4096,TextField.ANY);
DeviceName=new TextField("Device Name","",4096,TextField.ANY);
TextEditJSR75=new TextField("Edit Text","",10240,TextField.ANY);
FilePath=new TextField("File Location",FilePathLocationURL,256,TextField.ANY);
FileName=new TextField("File Name",FileNameData,256,TextField.ANY);

Exit.append("Do you want to exit this application?");
Exit.addCommand(cmdOK1);
Exit.addCommand(cmdBack);
Exit.setCommandListener(this);

TextJSR75.append(FilePath);
TextJSR75.append(FileName);
TextJSR75.append(TextEditJSR75);
TextJSR75.addCommand(cmdBack5);
TextJSR75.addCommand(cmdOp1);
TextJSR75.setCommandListener(this);

TextRMSOptions=new List("Text RMS Options",List.IMPLICIT);
TextRMSOptions.append("Save text",null);
TextRMSOptions.append("Load text",null);
TextRMSOptions.append("Clear text",null);
TextRMSOptions.append("Delete text",null);
TextRMSOptions.addCommand(cmdOK2);
TextRMSOptions.addCommand(cmdBack1);
TextRMSOptions.setCommandListener(this);

TextJSR75Options=new List("Text JSR75 Options",List.IMPLICIT);
TextJSR75Options.append("Save text",null);
TextJSR75Options.append("Save text with descriptor",null);
TextJSR75Options.append("Save configuration",null);
TextJSR75Options.append("Save all",null);
TextJSR75Options.append("Load text",null);
TextJSR75Options.append("Clear text",null);
TextJSR75Options.append("Delete text",null);
TextJSR75Options.addCommand(cmdBack6);
TextJSR75Options.addCommand(cmdOK4);
TextJSR75Options.setCommandListener(this);

TextRMS.append(TextEdit);
TextRMS.addCommand(cmdBack2);
TextRMS.addCommand(cmdOp);
TextRMS.setCommandListener(this);

Menu=new List("Menu",List.IMPLICIT);
Menu.append("Text in RMS",null);
Menu.append("Text in File System",null);
Menu.append("Settings",null);
Menu.append("Device Info",null);
Menu.append("Help",null);
Menu.append("About",null);
Menu.addCommand(cmdOK);
Menu.addCommand(cmdExit);
Menu.setCommandListener(this);

Settings.append(DeviceName);
functionLoadDeviceName();
Settings.append("Device name shows on text files that are created on this application");
Settings.addCommand(cmdSave1);
Settings.addCommand(cmdBack8);
Settings.setCommandListener(this);

Main.append("PaintCAD J2ME - Making application icon\n\nJ2ME IDE by DASH ANIMATION V2 - RMS example codeblock\n\nInternet - The basic coding skills in J2ME\n\nGoogle Gemini - Adding image to form ui\n\nnnproject - collecting api location from j2me api tester v0.7.11 (via viewing raw complied class file)");
Main.addCommand(cmdBack);
Main.setCommandListener(this);

DeviceInfo.append("Device name : "+DeviceName+"\n\nJ2ME Runtime\n\n");
appendProperty("MicroEdition Configuration", "microedition.configuration");
appendProperty("MIDP Profile", "microedition.profiles");
appendProperty("Platform", "microedition.platform");
DeviceInfo.append("\n\nJ2ME API\n\n");
checkApi("JSR-75 FileConnection", "javax.microedition.io.Connector", "file.conn.version");
checkApi("JSR-82 Bluetooth", "javax.bluetooth.LocalDevice", "bluetooth.api.version");
checkApi("JSR-135 MMAPI", "javax.microedition.media.Manager", "supports.audio.capture");
checkApi("JSR-184 Mobile 3D", "javax.microedition.m3g.Graphics3D", null);

DeviceInfo.addCommand(cmdBack7);
DeviceInfo.setCommandListener(this);
}

protected void startApp() {
Alert a=new Alert("","OneTime Text Editor\nVersion - 1.4\nTanvir Islam",null,AlertType.INFO);
a.setTimeout(2500);
d.setCurrent(a,Menu);
}


protected void pauseApp() {}
protected void destroyApp(boolean unconditional) {}

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
int selectedIndex2=TextJSR75Options.getSelectedIndex();
functionalTextJSR75Options(selectedIndex2); 
}
if (c==cmdBack6) {
showScreenTextJSR75(); 
}
if (c==cmdOp1) {
screenTextJSR75Options();
}
if (c==cmdBack7) {
commandBack();
}
if (c==cmdBack8) {
commandBack();
}
if (c==cmdSave1) {
functionSaveDeviceName();
alertSavedSettingsConfig();
}
}

//Application's core (screens, alerts, funtions and logic)

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

private void functionalTextJSR75Options(int index2) {
switch(index2) {
case 0:

break;
case 1:

break;
case 2:

break;
case 3:

break;
case 4:
functionLoadTextJSR75();
screenViewLoadedTextJSR75();
break;
case 5:

break;
case 6:

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
showScreenTextJSR75();
break;
case 2:
functionLoadDeviceName();
showScreenSettings();
break;
case 3:
screenDeviceInfo();
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

private void showScreenTextJSR75() {
screenTextJSR75();
}

private void showScreenSettings() {
screenSettings();
}

private void screenTextJSR75Options() {
d.setCurrent(TextJSR75Options);
}

private void screenTextRMSOptions() {
d.setCurrent(TextRMSOptions);
}

private void screenViewLoadedTextJSR75() {
TextEditJSR75.setString(LoadTextJSR75);
Alert LoadedJSR75=new Alert("","Your text file has been loaded from your device storage!",null,AlertType.INFO);
LoadedJSR75.setTimeout(2000);
d.setCurrent(LoadedJSR75,TextJSR75);
}

private void screenTextRMS() {
d.setCurrent(TextRMS);
}

private void screenTextJSR75() {
d.setCurrent(TextJSR75);
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

private void functionLoadTextJSR75() {
String FullFilePathURL=FilePathURL+FilePathLocationURL+FileNameData+FileFormat;
FileConnection fc=null;
InputStream is=null;
try {
fc=(FileConnection)Connector.open(""+FullFilePathURL,Connector.READ);
if(fc.exists()){
is=fc.openInputStream();
byte[] b=new byte[512];
int n=is.read(b);
LoadTextJSR75=new String(b,0,n);
is.close();
}
fc.close();
} catch (IOException e) { }
}

private void functionSaveTextJSR75() {
//Not started yet
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
RecordStore rs = null;
try {
rs = RecordStore.openRecordStore(DeviceNameData,false);
if(rs.getNumRecords()>0)
DeviceName.setString(new String(rs.getRecord(1)));
} catch(Exception e){}
finally {
try{if(rs!=null)
rs.closeRecordStore();}
catch(Exception ig){}
}
}

private void functionSaveDeviceName() {
RecordStore rs = null;
try {
rs = RecordStore.openRecordStore(DeviceNameData,true);
byte[] b =DeviceName.getString().getBytes();
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
ImageItem Win2000MCitem = new ImageItem("Windows2000MC",Win2000MC,ImageItem.LAYOUT_LEFT,"Error 121");
ImageItem DASHitem = new ImageItem("DASH ANIMATION V2",Dash,ImageItem.LAYOUT_LEFT,"Error 121");
About.append(MIDleticonitem);
About.append("Version - 1.4\nBy Tanvir Islam\n\nWritten and Compiled on J2ME SDK Mobile on Symphony D54+ !\nMade in Bangladesh\n");
About.append(Win2000MCitem);
About.append("\nThanks to :\n");
About.append(DASHitem);
About.append("For making J2ME IDE with Built-In Sinppets for easier coding!");
About.append("\n\nhttps://www.youtube.com/@windows2000_mc\n\nWindows2000MC Website\nhttps://sites.google.com/view/windows2000mc-bangladesh/home");
About.append("\n\nTo prevent getting errors on Nokia S60, S40 and Sony Ercisson java phones, please set the storage permission to Always for future updates...");
About.append("\n\nWhat's new\n\n1. Code was rewritten\n2. Added MIDlet icon\n3. Added Proper Menu System\n4. Added ability to take full control over text in RMS\n5. Added Device Info\n6. Added Settings");
About.append("\n\n\nCompletely written inside the MIDlet, no extra fucking classes to mess because i have zero skills of advanced coding...");
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
Help.append("OneTime Text Editor is a tool that you can write a text and save in various ways and it's easy!\n\n\nText in RMS\n\nThis option brings to you to the text field where you want to write and edit text in this field. If you click the right soft key or click 'Options' via touch screen, it will bring the options which you can take full control of your text!\n\nSave Text -> saves text in RMS Database\nLoad Text -> loads text from RMS Database\nClear Text -> clears text from text field\nDelete text -> clears text and saved in RMS Database");
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
}





























































































