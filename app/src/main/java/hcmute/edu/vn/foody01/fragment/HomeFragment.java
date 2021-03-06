package hcmute.edu.vn.foody01.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import hcmute.edu.vn.foody01.Goto;
import hcmute.edu.vn.foody01.R;
import hcmute.edu.vn.foody01.list_adapter.StoreAdapter;
import hcmute.edu.vn.foody01.model.Store;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    Button toMenuBtn;
    ListView storeList;

    ArrayList<Store> arrayStore;
    StoreAdapter adapter;

    Goto _goto;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _goto = (Goto) getActivity();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        storeList = (ListView) view.findViewById(R.id.storeListView);
        arrayStore = new ArrayList<Store>();
        AddArrayStore();
        adapter = new StoreAdapter(getActivity(), R.layout.store_item, arrayStore);
        storeList.setAdapter(adapter);

        storeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                _goto.GotoMenu(arrayStore.get(i));
            }
        });

        return view;
    }

    private void AddArrayStore(){
        arrayStore.add(new Store("Ong Bau", R.drawable.e563272c9ad83c099901902c0a7056eb,R.drawable.e563272c9ad83c099901902c0a7056eb, "Quan ngon UTE"));
        arrayStore.add(new Store("Dat Ngo", R.drawable.canteen, R.drawable.canteen,"Sieu Ngon"));
        arrayStore.add(new Store("Phuong Nam", R.drawable.seafood,R.drawable.seafood, "Quan ngon UTE"));
        arrayStore.add(new Store("Phuong Nam", R.drawable.quancom,R.drawable.quancom, "Quan ngon UTE"));
        arrayStore.add(new Store("Phuong Nam", R.drawable.canteen, R.drawable.canteen,"Quan ngon UTE"));
        arrayStore.add(new Store("Phuong Nam", R.drawable.seafood, R.drawable.seafood,"Quan ngon UTE"));
    }
}