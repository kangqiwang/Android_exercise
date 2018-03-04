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
