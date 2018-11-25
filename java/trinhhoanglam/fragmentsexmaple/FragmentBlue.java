package trinhhoanglam.fragmentsexmaple;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
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
 */
public class FragmentBlue extends Fragment implements FragmentCallbacks{
    // this fragment show a ListView
    MainActivity main;
    Context context = null;
    String message  = "";
    TextView txtBlue;

    // ListView data
    private String items[] = {
            "Line-0", "Line-1", "Line-2",
            "Line-3", "Line-4", "Line-5",
            "Line-6", "Line-7", "Line-8",
    };

    // convenient constructor(accept arguments, copy them to a bundle, binds bundle to fragment)
    public static FragmentBlue newInstance(String strArg) {
        FragmentBlue fragment = new FragmentBlue();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity();
            main = (MainActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implements callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout layout_blue = (LinearLayout) inflater.inflate(R.layout.fragment_blue, null);
        // plumbing – get a reference to textview and listview
        txtBlue = (TextView) layout_blue.findViewById(R.id.txtBlue);
        ListView lw = (ListView) layout_blue.findViewById(R.id.lwBlue);
        lw.setBackgroundColor(Color.parseColor("#ffccddff"));
        // define a simple adapter to fill rows of the listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, items);
        lw.setAdapter(adapter);
        // show listview from the top
        lw.setSelection(0);
        lw.smoothScrollToPosition(0);
        // react to click events on listview’s rows
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                main.onMsgFromFragToMain("BLUE-FRAG", "Blue selected row=" + position);
                txtBlue.setText("Blue selected row=" + position);
            }
        });

        // do this for each row (ViewHolder-Pattern could be used for better performance!)
        return layout_blue;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {
        String message = "THIS MESSAGE COME FROM MAIN: " + strValue;
        txtBlue.setText(message);
    }
}
