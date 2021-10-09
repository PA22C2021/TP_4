package gui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_4.R;
import com.example.tp_4.domain.BaseDatos;
import com.example.tp_4.domain.Categoria;
import com.example.tp_4.domain.Producto;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificacionFragment extends Fragment {

    Spinner spCategoriaEdicion;
    EditText etIdModificacion, etNombreProducto, etStock;
    Button btnBuscar, btnModificar;
    BaseDatos baseDatos;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ModificacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModificacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModificacionFragment newInstance(String param1, String param2) {
        ModificacionFragment fragment = new ModificacionFragment();
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

        View view = inflater.inflate(R.layout.fragment_modificacion, container, false);

        // Me traigo el control del fragment.
        etIdModificacion = (EditText) view.findViewById(R.id.etIdModificacion);
        btnBuscar = (Button) view.findViewById(R.id.btnBuscar);
        etNombreProducto = (EditText) view.findViewById(R.id.etNombreModificacion);
        etStock = (EditText) view.findViewById(R.id.etStockModificacion);
        spCategoriaEdicion = (Spinner) view.findViewById(R.id.spCategoriaModificacion);
        btnModificar = (Button) view.findViewById(R.id.btnModificar);

        baseDatos = new BaseDatos();
        baseDatos.SetCategoriasSpinner(view, spCategoriaEdicion);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuscarProducto(v);
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ModificarProducto(v);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void BuscarProducto(View view)
    {
        String id = etIdModificacion.getText().toString();

        if (id.equals(""))
        {
            Toast.makeText(view.getContext(), "Ingrese un id para buscar el producto.", Toast.LENGTH_SHORT).show();
            return;
        }

        baseDatos.SetProductoId(view, etIdModificacion, etNombreProducto, etStock, spCategoriaEdicion);
    }

    public void ModificarProducto(View view)
    {
        String id = etIdModificacion.getText().toString();

        if (id.equals(""))
        {
            Toast.makeText(view.getContext(), "Ingrese un id para modificar el producto.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(ValidateFields(view)){
            // TODO: Grabar en base de datos
            baseDatos.ModifyProductoId(view, etIdModificacion, etNombreProducto, etStock, spCategoriaEdicion);
            Toast.makeText(view.getContext(), "Producto modificado", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean ValidateFields(View view){
        boolean valid = true;
        if(etNombreProducto.getText().toString().isEmpty() && etStock.getText().toString().isEmpty() && spCategoriaEdicion.getSelectedItem().toString().isEmpty()){
            Toast.makeText(view.getContext(),"Debe completar los campos requeridos", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if(etNombreProducto.getText().toString().isEmpty()){
            Toast.makeText(view.getContext(),"Debe completar nombre del prducto", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if(etStock.getText().toString().isEmpty()){
            Toast.makeText(view.getContext(),"Debe completar stock del prducto", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if(spCategoriaEdicion.getSelectedItem().toString().isEmpty()){
            Toast.makeText(view.getContext(),"Debe seleccionar la categoria del prducto", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }
}