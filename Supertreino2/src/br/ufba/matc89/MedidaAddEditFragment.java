package br.ufba.matc89;

import br.ufba.matc89.model.Medida;
import br.ufba.matc89.util.DateUtil;
import br.ufba.matc89.util.NumberUtil;
import br.ufba.matc89.dao.MedidaDAO;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

public class MedidaAddEditFragment extends Fragment {

	protected EditText medida_data_afericao;
	protected EditText medida_peso;
	protected EditText medida_altura;
	protected EditText medida_cintura;
	protected EditText medida_quadril;
	protected EditText medida_subescapular;
	protected EditText medida_tripicial;
	protected EditText medida_peitoral;
	protected EditText medida_axilar_media;
	protected EditText medida_supra_iliaca;
	protected EditText medida_abdominal;
	protected EditText medida_antebraco;
	protected Button salvarButton;
	protected Medida medida = new Medida();
	MedidaDAO dbMedida;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_medida_add_edit, container, false);
		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		dbMedida = new MedidaDAO(getActivity());
		
		medida_data_afericao = (EditText)getView().findViewById(R.id.medida_data_afericao);
		DateUtil.validarDataEmPreenchimento(medida_data_afericao, "##/##/####");
		medida_peso = (EditText)getView().findViewById(R.id.medida_peso);
		medida_altura = (EditText)getView().findViewById(R.id.medida_altura);
		medida_cintura = (EditText)getView().findViewById(R.id.medida_cintura);
		medida_quadril = (EditText)getView().findViewById(R.id.medida_quadril);
		medida_subescapular = (EditText)getView().findViewById(R.id.medida_subescapular);
		medida_tripicial = (EditText)getView().findViewById(R.id.medida_tripicial);
		medida_peitoral = (EditText)getView().findViewById(R.id.medida_peitoral);
		medida_axilar_media = (EditText)getView().findViewById(R.id.medida_axilar_media);
		medida_supra_iliaca = (EditText)getView().findViewById(R.id.medida_supra_iliaca);
		medida_abdominal = (EditText)getView().findViewById(R.id.medida_abdominal);
		medida_antebraco = (EditText)getView().findViewById(R.id.medida_antebraco);
		
        Bundle bundle=getArguments(); 
        if(bundle != null){
	        long id_medida = bundle.getLong("id_medida",-1);
			if (id_medida != -1) {
				medida = dbMedida.getById(id_medida);
				medida_data_afericao.setText(DateUtil.convertToDateForm(medida.getDataAfericao()));
				medida_peso.setText(Double.toString(medida.getPeso()));
				medida_altura.setText(Double.toString(medida.getAltura()));
				medida_cintura.setText(Double.toString(medida.getCintura()));
				medida_quadril.setText(Double.toString(medida.getQuadril()));
				medida_subescapular.setText(Double.toString(medida.getSubescapular()));
				medida_tripicial.setText(Double.toString(medida.getTripicial()));
				medida_peitoral.setText(Double.toString(medida.getPeitoral()));
				medida_axilar_media.setText(Double.toString(medida.getAxilarMedia()));
				medida_supra_iliaca.setText(Double.toString(medida.getSupraIliaca()));
				medida_abdominal.setText(Double.toString(medida.getAbdominal()));
				medida_antebraco.setText(Double.toString(medida.getAntebraco()));
			}
        }
		
		salvarButton = (Button) getView().findViewById(R.id.salvar);
		salvarButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				attemptSave();
			}
		});
	}
	
	public void attemptSave(){
		boolean cancel = false;
		View focusView = null;
		
		if (TextUtils.isEmpty(medida_data_afericao.getText().toString())) {
			medida_data_afericao.setError(getString(R.string.error_field_required));
			focusView = medida_data_afericao;
			cancel = true;
		}
		
		if(cancel){
			focusView.requestFocus();
		} else {
			medida.setDataAfericao(DateUtil.convertToDateSQL(medida_data_afericao.getText().toString()));
			medida.setPeso(NumberUtil.parseDouble(medida_peso.getText().toString()));
			medida.setAltura(NumberUtil.parseDouble(medida_altura.getText().toString()));
			medida.setCintura(NumberUtil.parseDouble(medida_cintura.getText().toString()));
			medida.setQuadril(NumberUtil.parseDouble(medida_quadril.getText().toString()));
			medida.setSubescapular(NumberUtil.parseDouble(medida_subescapular.getText().toString()));
			medida.setTripicial(NumberUtil.parseDouble(medida_tripicial.getText().toString()));
			medida.setPeitoral(NumberUtil.parseDouble(medida_peitoral.getText().toString()));
			medida.setAxilarMedia(NumberUtil.parseDouble(medida_axilar_media.getText().toString()));
			medida.setSupraIliaca(NumberUtil.parseDouble(medida_supra_iliaca.getText().toString()));
			medida.setAbdominal(NumberUtil.parseDouble(medida_abdominal.getText().toString()));
			medida.setAntebraco(NumberUtil.parseDouble(medida_antebraco.getText().toString()));
			
			dbMedida.save(medida);
			//TODO usar o controller para salvar medida
			
			getActivity().getSupportFragmentManager().popBackStack();
		}
	}
}
