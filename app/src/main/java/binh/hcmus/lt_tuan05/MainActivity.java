package binh.hcmus.lt_tuan05;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MainCallbacks{

    Fragment_Class frag_class;
    Fragment_Student frag_student;
    FragmentTransaction fragtran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragtran = getFragmentManager().beginTransaction();
        frag_class = Fragment_Class.newInstance();
        //fragtran.replace(R.id.frag_class, frag_class);
        fragtran.commit();

        fragtran = getFragmentManager().beginTransaction();
        frag_student = Fragment_Student.newInstance();
        fragtran.replace(R.id.frag_student, frag_student);
        fragtran.commit();
    }

    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
        if (sender.equals("Frag-Class")) {
             frag_student.onMsgFromMainToFragment(strValue);
        }
        if (sender.equals("Frag-Student")) {
            frag_class.onMsgFromMainToFragment(strValue);
        }
    }
}
