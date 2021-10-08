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
import android.widget.EditText;

import com.example.tp_4.R;
import com.example.tp_4.domain.BaseDatos;
import com.example.tp_4.domain.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AltaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AltaFragment extends Fragment {

    private EditText et_id, et_nombre, et_stock;
    private Spinner spCategoriaAlta;
    private Button btnAgregar;

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
        et_id = (EditText) view.findViewById(R.id.etIdAlta);
        et_nombre = (EditText) view.findViewById(R.id.etNombreAlta);
        et_stock = (EditText) view.findViewById(R.id.etStockAlta);
        spCategoriaAlta = (Spinner) view.findViewById(R.id.spCategoriaAlta);

        SetCategoriasSpinner(view);

        try {
            Connection conectionBD = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10442660", "sql10442660", "5vu3EXhTp4");
        } catch (SQLException throwables)
        {
            Toast.makeText(view.getContext(), throwables.toString(), Toast.LENGTH_SHORT).show();
        }

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
        // TODO: Validar si existe el ID en la BBDD
        if(ValidateFields(view)){
            // TODO: Grabar en base de datos
            Toast.makeText(view.getContext(), "Producto agregado", Toast.LENGTH_SHORT).show();

            ClearFields();
        }
    }

    private boolean ValidateFields(View view){
        boolean valid = true;
        if(et_id.getText().toString().isEmpty() && et_nombre.getText().toString().isEmpty() &&
                et_stock.getText().toString().isEmpty() && spCategoriaAlta.getSelectedItem().toString().isEmpty()){
            Toast.makeText(view.getContext(),"Debe completar los campos requeridos", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if(et_id.getText().toString().isEmpty()){
            Toast.makeText(view.getContext(),"Debe completar el id del prducto", Toast.LENGTH_SHORT).show();
            valid = false;
        }else{
            if(new BaseDatos().ValidateExistID(et_id.getText().toString())){
                Toast.makeText(view.getContext(),"El id ingresado ya existe", Toast.LENGTH_SHORT).show();
                valid = false;
            }
        }

        if(et_nombre.getText().toString().isEmpty()){
            Toast.makeText(view.getContext(),"Debe completar nombre del prducto", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if(et_stock.getText().toString().isEmpty()){
            Toast.makeText(view.getContext(),"Debe completar stock del prducto", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if(spCategoriaAlta.getSelectedItem().toString().isEmpty()){
            Toast.makeText(view.getContext(),"Debe seleccionar la categoria del prducto", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }

    private void ClearFields(){
        et_id.setText("");
        et_nombre.setText("");
        et_stock.setText("");
        spCategoriaAlta.setSelection(-1);
    }
}