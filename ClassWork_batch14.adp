
Welcome to APSSDC Android Application Development
		Android Application Development(AAD)
		Studnets Training Program(STP)
		Batch 14 Timings 4.00 Pm to 6.00Pm
		From 24th Aug 2020 to 12th Sept 2020
Day-1
====Instuctions====
1.Post the doubts on the chats box
2.mute mic and turn of the webcam 
3.user name:Name-Roll number-College name
what is Android?
Android is Platform which contains the Mobile Oparating System based on Linux Kernal
History of the Android:
In year of 2005 -Android Inc 
Key role of the Android Development:
	1.Andy Rubin 
	2.Nic Sear
	3.Rich Minar
	4.Crish White 

For a startup developing an OS on Digital Camera 
2007-Companey went deep losses 
2007-They changed idea to developing OS for mobile 
2007- Google has aqvired this Android Inc 
2008-Google Realeased a first Android OS based mobile
	HTC-HTC-Dream 
Google Started Android OS Names as Alphabits
Alpha 
Beta
Cupcake
dount
eclar
froyo
gigger bread
honey comb
icecream sandwich
Jelly bean 
Kitkat
Lolipop
MarshMellow
Nougat
Oreo--->Google has stopped os Names as Alphabits
Pie--->
Q Android 10
R Android 11

Google->Google Android developer Fundamentals v2

MVC-Model View Controller 
MVVM-Model View ViewModel 
Androd Stack is 4 layers
1.System Apps And User apps:
	System apps:There is no special Status
		ex:messges,clock,calculator ,settings ..etc
	USer Apps:User can dowload from external ..
		ex:social medi apps,share it,..etc

2.API FRAMEWORK:Kotiln,Java->API appliction Programming Interface 
	It is proviing the Interface between User and OS

3.Runtime and Native lib..:each app runs its own Process ..

4.HAL(Harware Abstration Layer):It is bridge between the Software Componts And hardware componts 
	Ex:Camera -H/W
		Button -S/w
5.Linux Kernal:To manage the all the Resourses 
	memory
	Power
	Processor ..etc
	pubg:it reqvired high processor 
			1gb-ram 4 gb
	whstup-50mb -60Mb
		2gb ram 

Both which type :Script ->pulish
HTML-Predefined Tags
	Head-<Html></Html>
	Title -
	Head-
	Body-
XML-user Defined Tags:
	For ex:Laptop 
	<Laptop 
			id:3565
			name:Lennova
			hight:15.5
			witdth:12
			>
		</Laptop>
XMLNS-naming Schema:
	Schema-->Varibale name
	=======================================================
				Day-2
Welcome to APSSDC Android Application Development
			Good Evening To All

user name:Name-Roll number-College name

Today objective :
	Basic Building Blocks
	App components
	About Android Studio IDE 
	Create Hello world APp 
	How to excute the Hello APp Application

Basic Building Blocks:4 types 
	1.Resourses:img,colors,styles..etc
	2.App components:
		1.Screens(Activity)
		2.Services,
		3.BroadCast Recivers
		4.Content Provider
	3.Manifest:this the one of the xml file.in this file have total app components information
		Runtime permissions will handle here
		Internet connetion 
	4.Build Configuration :total app information 
	 
App components:
		1.Screens(Activity):Every Single screen is like as an Acivity with User Interface
			ex:buttons,textview,imageview...etc 
		2.Services:A service is a long running background task without user intraction
			ex:Music,Notification,Alarm,dowloading ..etc..
		3.BroadCast Recivers:BroadCast Signals --Android System will Release the Announecments 
			ex:Lock,Unlock,PowerConnected,Dissconned,...etc
			ex2:Radio Station ->Radio channel Signals 
				92.5,93.5...106.2...etc
				Use Can Recives the signal through the aanteena (Headset)

		4.Content Provider:Provide/Share  the content between two apps
		contents is contacts,photos,audio/video files,doc..etc
			whstup,hike,messenger,...etc
About Android Studio IDE :
---------------------------
Android Studio:4 parts 
	1.Android studio Panel:File,edit,build,avd..etc
	2.project files:it will have the entire project files 
	3.Layout editor:we can design the screens /Coding
	4.Logcat / Monitor :log info and have there errors info..

	Day -3 
	=================
	Layout:
	View ,ViewGroup
	Our first own app


----------------------
Day -5 
The Acivity Life cycles
6 types
1.onCreate():By Defaulty app can create 
2.onStart():Start the Activity 
3.onResume():In this method user can interact 
4.onPause():this is invisible
5.onStop():App can stopped 
6.onDestroy():Remove the stack
-------------------------------
Good Morning To all 
			Day-6

Intent: An intent is intenstion to do some oparation.To navigating between two activitys/Screens 
advantages of intents:
we can start the activitys,services,send the BroadCast Recivers

Types of the Intents:
	2 Types :
		1.Explicit Intent:We can navigate with in the app screen:
			ex:Shopping app :
					search the product--screen
					add to cart -------screen
					payment      -------screen		
			here we know the source and desti..

		Syntax:Intent intentObj=new Intent(search.class,PaymentScreen.Class)
				startActivity(intentObj)
		2.Implicit Intent:It starts a specific activity
		system will ask to find the destination screen
		It will be navigate to one app screen to other app screen 
		ex:"tommorow we have a class "-->msg
			whstup group,mail,telegram,msg..etc
		Sysntax:Intent intentObj=new Intent(action,Uri);
				startActivity(intentObj)
-----------------
Good Evening to all 
welcome Day 7 Android Workshop
All of you open the android studio

Note the Assignments :
Assignments:
1.In Hello Toast app
	when count button click increase the count value in he textview and Display that count value in the Toast message .
2.Simple Calculator app 
	Take two inputs and perform basic arthametic oparations .Result value set in the textview
	Note:If rotate the divice store the data
3.In the UI Components check the Switch button functionality i.e., working or not 


Mahammad Althaf Shaik 18091a0493 
--------------------------------------
Good Evening to all
			Day-8 
all of you open the Android studio

3.In the UI Components check the Switch/Toggle 
button functionality i.e., working or not


--------Day 12-----------
Good Evening to all
open the android studio and Google slide no. 4.5
Recyclerview Components:
6 types:
1.Data:some of data set for scrolling
2.Recyclerview :One of the View Class
3.Layout :For each row or item need to have one design
4.Layout manager:It manage the each Layout
5.Adapter:it will provide the access between data and Recyclerview
6.ViewHolder:It will be hold the view and unhold 
## Assignments 
1.Toast message when clicked on count button in Hello toast
2.Implementing Basic Calculator
3.Check functionality of switch
4.Recyclerview of android versions and names
5.Designing college app using TabNavigation
6.Login and Registration page using Shared preferences
