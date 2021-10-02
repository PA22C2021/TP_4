package gui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_4.R;
import com.example.tp_4.domain.BaseDatos;
import com.example.tp_4.domain.Categoria;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AltaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AltaFragment extends Fragment {

    Spinner spCategoriaAlta;
    Button btnAgregar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AltaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AltaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AltaFragment newInstance(String param1, String param2) {
        AltaFragment fragment = new AltaFragment();
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

        View view = inflater.inflate(R.layout.fragment_alta, container, false);

        // Me traigo el control del fragment.
        btnAgregar = (Button) view.findViewById(R.id.btnAlta);
        spCategoriaAlta = (Spinner) view.findViewById(R.id.spCategoriaAlta);

        SetCategoriasSpinner(view);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AgregarProducto(v);
            }
        });

        return view;
    }

    private void SetCategoriasSpinner(View view)
    {
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, new BaseDatos().GetCategorias());
        spCategoriaAlta.setAdapter(adapter);
    }

    public void AgregarProducto(View view)
    {
        // TODO: Agregar validaciones

        // TODO: Grabar en base de datos
        Toast.makeText(view.getContext(), "Producto agregado", Toast.LENGTH_SHORT).show();

        // Todo: Limpiar campos
    }
}