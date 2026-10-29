# OneTime Text Editor J2ME
OneTime Text Editor is an utility application for J2ME Phones and also Emulator [.e.g J2ME Loader]. This app uses High level UI (also known as Form ui) which doesn't required high-end phones. This is a normal text editor but it can save your text in RMS. You can take full control over your text. Also you can check your device's java API in this application. Also it has HTTP Connection support.

# Features

1. Supports saving and loading text from RMS Database
2. HTTP Connection Support
3. HTTPS Connection Support (Device dependent)
4. Full Local Account on device support
5. Has Easter Eggs (type "3.14" in 'Password' screen)
6. Viewing RMS Data (broken, not implemented)

# Project Distribution 

This Project is a open-source and freeware. you can redistribute it and/or modify it!

# Development 

Coded on J2ME SDK Mobile on Symphony D54+

PaintCAD J2ME - Making Icons
MiniCommander J2ME - File Management

# Major Flaws

1. Less Memory to compile - Symphony D54+ can give about 1.8 Megabytes of Java Heap Memory (1865 KB). This is ok for compiling 1 large MIDlet file or 2 to 3 smaller class files. but if the .java files became too large in each development phase, Compiling it takes alot of time and takes up way too much memory. In the last development phase of OneTime Text Editor, It took about 236 seconds to build the application, and any further development or bug fixing may failed to compile or 'Out of Memory' crash

2. Slow Development - Symphony D54+ uses MT6260 chipset which is a MediaTek chip with clock speed about 300 to 350 MHz. Running J2ME SDK Mobile on this java phone is not too bad if the .java file and the project itself is small but for coding this huge MIDlet is very pain to code because of the lagging to handle the app and may have a chance to freeze itself or crashing the entire phone.

3. Alternative solutions - Since compiled a large MIDlet in such small memory may not gonna work, Finding a J2ME Compiler with Java 1.1 support is hard to find on online as J2ME is been a discontinued platform. Then i found out there are few people made these compilers for Android phones. One of them was J2ME Builder IDE for Android. Made by DASH ANIMATION V2, This app is entire J2ME IDE with compiler. Working with it felt ok but getting compiling errors that are not what i used to get. So for 1.6 update will have 2 separate versions.

# Versions

1. 1.4 - Original concept and phototype version

2. 1.5 - Similar version but with Network capabilities

3. 1.6 -

   1.6-Stable - The last version ever compiled on J2ME SDK Mobile on Symphony D54+. Features Full Local Account support on device, Easter Eggs, Adding a title for the text in RMS. Also it is very stable and has less bugs.

   1.6-ECJ - The version was compiled on J2ME Builder IDE for Android with ECJ Compiler. But it has some extra features that was not implemented such as "Explore more..." in menu which doesn't work. Also it has some bugs due to bad compiling such as "Quick Settings" in Text in RMS options redirects to the Password screen. But all function are work the same as 1.6-Stable

# Image section (1.5 Update)

<img width="2448" height="3264" alt="1000053845" src="https://github.com/user-attachments/assets/4c3e1835-c6fd-493c-86fc-416e45002586" />
<img width="2448" height="3264" alt="1000053842" src="https://github.com/user-attachments/assets/69f35d26-9911-47e9-8aa9-c341d444979c" />
<img width="2448" height="3264" alt="1000053844" src="https://github.com/user-attachments/assets/cf286f5c-726c-4253-aca7-2e48b4fa1aff" />
<img width="2448" height="3264" alt="1000053843" src="https://github.com/user-attachments/assets/d0a566ab-a2a6-4c86-8d54-2eca970749db" />
<img width="2448" height="3264" alt="1000053841" src="https://github.com/user-attachments/assets/345a2327-db14-4373-b937-700666f" />
<img width="2448" height="3264" alt="1000053846" src="https://github.com/user-attachments/assets/443a5276-7731-4dfe-ab12-98f3e53fc5c1" />


