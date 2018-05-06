package binh.hcmus.lt_tuan05;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Class.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Class#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Class extends Fragment implements FragmentCallbacks{

    // this fragment shows a ListView
    MainActivity main;
    Context context = null;
    String message = "";
    int count;
    ListView listView;

    // data to fill-up the ListView
    private String[] items = {"Lớp 1", "Lớp 2", "Lớp 3", "Lớp 4", "Lớp 5", "Lớp 6", "Lớp 7", "Lớp 8", "Lớp 9"};
    private String[] lists = {"Nguyễn Văn A", "Nguyễn Văn B", "Nguyễn Văn C", "Nguyễn Văn D",
            "Nguyễn Văn E", "Nguyễn Văn F", "Nguyễn Văn G", "Nguyễn Văn H", "Nguyễn Văn Q"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_Class() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Class.
     */

    public static Fragment_Class newInstance() {
        Fragment_Class fragment = new Fragment_Class();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        main = (MainActivity) getActivity();
        count = -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // inflate res/layout_blue.xml to make GUI holding a TextView and a ListView
        LinearLayout Fragment_Class = (LinearLayout) inflater.inflate(R.layout.fragment_fragment__class, null);

        listView = Fragment_Class.findViewById(R.id.ListViewClass);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setSelection(0);
        main.onMsgFromFragToMain("LEFT-FRAG", lists[0]);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView textView = (TextView) listView.getChildAt(position);

                if(count!=-1){
                    textView = (TextView) listView.getChildAt(count);

                }
                count = position;
                main.onMsgFromFragToMain("LEFT-FRAG", lists[position]);
            }
        });

        return Fragment_Class;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {
        if(strValue.equals("first")){
            count = 0;
        }else if(strValue.equals("previous")){
            if(count>0){
                count--;
            }
        }else if(strValue.equals("next")){
            if(count<items.length-1){
                count++;
            }
        }else if(strValue.equals("last")){
            count = items.length - 1;
        }

        listView.setSelected(true);
        listView.setSelection(count);
        main.onMsgFromFragToMain("LEFT-FRAG", lists[count]);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
