package tutorials;

import java.util.ArrayList;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.ImageView;

import com.ube.salinlahifour.R;

public abstract class AbstractTutorialActivity extends Activity {
	ArrayList<ImageView> screenshots;
	ArrayList<SoundPool> voiceovers;
	ArrayList<String> description;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abstract_tutorial);
	}
}
