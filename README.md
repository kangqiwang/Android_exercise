# Android_exercise
my Android exercise
##	<animation-list/>
how to deal with the wrong <animation-list>

put files into the folder of drawable, which I deal with it. Of course, you have to change some source code in other files.

## Moulde


## Intent

* Ecplicit Intents have specified a component

`

	<activity android:name=".FirstActivity">
		<intent-filter>
			<action android:name="android.intent.action.MAIN" />
			<category android:name="android.intent.category.LAUNCHER" />
		</intent-filter>
	</activity>
	
	<activity android:name=".SecondActivity">
	</activity>

	Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
	startActivity(intent);

`

* Implicit Intents have not specified a component
 
`

	<activity android:name=".SecondActivity">
		<intent-filter>
			<action android:name="com.example.activitytest.ACTION_START" />
			<category android:name="android.intent.ccategory.DEFAULT" />
			<category android:name="com.example.activitytest.MY_CATEGORY" />
		</intent-filter>
	</activity>
	
	Intent intent = new Intent("com.example.activitytest.ACTION_START");
	intent.addCategory("com.example.activitytest.MY_CATEGORY");
	startActivity(intent);

`

starting other program using Implicit Intents

`

	button1.setOnClickListener(new View.OnClickListener(){
		@override
		public void onClick(View v){
			Intent intent=new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("https://www.google.com"));
			startActivity(intent);
		}
	});

`
except HTTP, we also can use 'geo' and 'tel' to represent location and dialing.

`
	
	button1.setOnClickListener(new View.OnClickListener(){
		@override
		public void onClick(View v){
			Intent intent=new Intent(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel: 07759685947"));
			startActivity(intent);
		}
	});


`

save the data

`

	button1.setOnClickListener(new View.OnClickListener(){
		@override
		public void onClick(View v){
			String data="Hello SecondActivity"
			Intent intent=new Intent(FirstActivity.this, SecondActivity.class);
			intent.putExtra("extra_data", data);
			startActivity(intent);
		}
	});

`

take the data

`

	public class SecondActivity extends AppCompatActivity{
	
		@override
		protected void onClick(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			setContentView(R.layout.second_layout);
			Intent intent= getIntent();
			String data = intent.getStringExtra("extra_data");
			Log.d("SecondActivity", data);
		}
	}
`

return data

		setResult(RESULT_OK, intent);



life cycle

	onCreate()
	onStart()
	onResume()
	onPause()
	onStop()
	onDestory()
	onRestart()

Android Activity's launchMode

	standard 
	singletop
	singletask
	singleinstance	

class ActivityCollector

	public class ActivityCollector {
		public static List<Activity> activities = new ArrayList<>();
		public static void addActivity(Activity activity){
			activities.add(activity);
		}
		public static void removeActivity(Activity activity){
			activities.remove(activity);
			}
		public static void finishAll() {
			for (Activity activity : activities) {
				if (!activity.isFinishing()){
					activity.finish();
				}
			}
		}
	}

	public class BaseActivity extends AppCompatActivity {
		@override
		protected void onCreate(Bundle savedInstanceState){
			supet.onCreate(savedInstanceState);
			Log.d("BaseActivity", getClass().getSimpleName());
			ActivityCollector.addActivity(this);
		}
		@Override
		protected void onDestroy() {
			super.onDestroy();
			ActivityCollector.removeActivity(this);
		}
	}


UI Interface

TextView
	android:gravity="center"
	//center
	android:textSize="24sp"// font size
	android:textColor="#00ff00"// font color

Button
	android:textAllCaps="false"// Prohibit Uppercase conversion
	
	//a way to listen click
	Button button = (Button) findViewById(R.id.button);
	button.setOnClickListener(new View.OnclickListener) {
		@Override
		public void onClikc(View v)	{
			//add action
		}
	}
	
	// another way to listen click
	public class MainActivity extends AppCompatActivity implements View.OnClickListener{
		@Override
		protected void onCreate(Bundle savedInstanceState)	{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			Button button = (Button) findViewById(R.id.button);
			button.setOnClickListener(this);
		}
		@Override
		public void onClick(View v){
			switch (v.getId()){
				case R.id.button:
				//add action
					break;
				default:
					break;
			}
		}
	}
	
EditText
	android:hint="Type something here"
	//hint on editext

	anroid:maxLines="2"

ImageView

image in the folder (drawable)

	android:src="@drawable/img_1"

	//change images in ImageView

	private ImageView imageView;
	imageView = (ImageView) findViewById(R.id.image_view);
	imageView.setImageResource(R.drawable.img_2);


ProgressBar
	//disappear progressBar --setVisibility()

	private ProgressBar progressBar;
	progressBar = (Progressar) findViewById(R.id.progress_bar);

	if(progressBar.getVisibility()==View.GONE){
		progressBar.setVisibility(View.VISIBLE);
	}else{
		progressBar.setVisibility(View.GONE);
	}

AlerDialog

	AlertDialog.Builder dialog=new AlerDialog.Builder(MainActivity.this);
	dialog.setTitle("This is Dialog");
	dialog.setMessage("Something important");
	dialog.setCancelable(false);
	dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
		@Override
		public void onClick(DialogInterface dialog, int which){
			
		}
	});
	dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
		@OVerride
		public void onClick(DialogInterface dialog, int which){}
	});
	dialog.show();

ProgressDialog

	//similar with AlerDialog
	ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
	progressDialog.setTitle("This is ProgressDialog");
	progressDialog.setMessage("Loading...");
	progressDialog.setCancelable(true);// can cancel by pressing back, function dismiss() is for close the dialog when the data have been loaded.
	progressDialog.show();

Layout

* LinearLayout

	<LinearLayout xmls:android="http://schemas.android.com/apk/res/android"
		android:orientation="vertical"
		android:orientation="horizontal"
		android::layout_gravity
		
		android:layout_weight="1"
		//for example, EditText are the screen of width 3/5, Button 2/5 ,<EditText> layout_weight=3, <Button> layout_weight=2

* RelativeLayout
* FrameLayout
* PrecentFrameLayout



## Broadcast Receiver

### normal broadcasts

### ordered broadcasts

	public class MainActivity extends AppCompatActivity {
		private IntentFilter intentFilter;
		private NetworkChangeReceiver networkChangeReceiver;
		@Override
		protected void onCreate(Bundle savedInstanceState){
			super.onCreate(saveInstanceState);
			setContentView(R.layout.activity_main);
			intentFilter = new IntentFilter();
		}
	}
全局广播

本地广播
localBroadcastManager to control everything

SharedPreferences

1. Context  getSharePreferences()
2. Activity getSharePreferences()
3. Preferences getDefaultSharedPreferences()

# Our project for NHS

some suggestion for my dissertation:

- central project back end database with data extraction facility
 
- central tool to generate and disseminate messages to individual users or groups of users

- app data holder in each mobile phone
 
- communication between app and central database
 
- user display interface for data

- interface to receive and display messages from central database
 
- interface to receive messages from other users (one or many)

- interface to add friends to peer group

database:


users_id | steps | groups | comments


account | password | users_id

the **data holder** will be hold in mobile and it will post on service.

the **users** will allow to login in their facebook account or twitter account or google account.
