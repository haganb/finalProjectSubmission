package pkgApp.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.poi.ss.formula.functions.FinanceLib;

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import javafx.beans.value.*;

import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {

	private RetirementApp mainApp = null;
	@FXML
	private TextField txtSaveEachMonth;
	@FXML
	private TextField txtYearsToWork;
	@FXML
	private TextField txtAnnualReturnWorking;
	@FXML
	private TextField txtWhatYouNeedToSave;
	@FXML
	private TextField txtYearsRetired;
	@FXML
	private TextField txtAnnualReturnRetired;
	@FXML
	private TextField txtRequiredIncome;
	@FXML
	private TextField txtMonthlySSI;

	private HashMap<TextField, String> hmTextFieldRegEx = new HashMap<TextField, String>();

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Adding an entry in the hashmap for each TextField control I want to validate
		// with a regular expression
		// "\\d*?" - means any decimal number
		// "\\d*(\\.\\d*)?" means any decimal, then optionally a period (.), then
		// decmial
		hmTextFieldRegEx.put(txtYearsToWork, "\\d*?");
		hmTextFieldRegEx.put(txtAnnualReturnWorking, "\\d*(\\.\\d*)?");

		// Check out these pages (how to validate controls):
		// https://stackoverflow.com/questions/30935279/javafx-input-validation-textfield
		// https://stackoverflow.com/questions/40485521/javafx-textfield-validation-decimal-value?rq=1
		// https://stackoverflow.com/questions/8381374/how-to-implement-a-numberfield-in-javafx-2-0
		// There are some examples on how to validate / check format

		Iterator it = hmTextFieldRegEx.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			TextField txtField = (TextField) pair.getKey();
			String strRegEx = (String) pair.getValue();

			txtField.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
						Boolean newPropertyValue) {
					// If newPropertyValue = true, then the field HAS FOCUS
					// If newPropertyValue = false, then field HAS LOST FOCUS
					if (!newPropertyValue) {
						if (!txtField.getText().matches(strRegEx)) {
							txtField.setText("");
							txtField.requestFocus();
						}
					}
				}
			});
		}

		//
		// TODO: Validate Working Annual Return %, accept only numbers and decimals
		txtAnnualReturnWorking.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        if (!newValue) { //when focus lost
	            if(!txtAnnualReturnWorking.getText().matches("[0-9](\\.[0-9]{1,2}){0,1}|10(\\.0{1,2}){0,1}")){
	            	txtAnnualReturnWorking.setText("");
	            }
	        }

	    });
		
		
		// TODO: Validate Years retired, accepted only decimals
		txtYearsToWork.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        if (!newValue) { //when focus lost
	            if(!txtYearsToWork.getText().matches("[0-9]|[0-3][0-9]|40")){
	            	txtYearsToWork.setText("");
	            }
	        }

	    });
		
		txtYearsRetired.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        if (!newValue) { //when focus lost
	            if(!txtYearsRetired.getText().matches("[0-9]|1[0-9]|20")){
	            	txtYearsRetired.setText("");
	            }
	        }

	    });
		
		// TODO: Validate Retired Annual Return %, accept only numbers and decimals
		txtAnnualReturnRetired.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        if (!newValue) { //when focus lost
	            if(!txtAnnualReturnRetired.getText().matches("[0-9](\\.[0-9]{1,2}){0,1}|10(\\.0{1,2}){0,1}")){
	            	txtAnnualReturnRetired.setText("");
	            }
	        }

	    });
		// TODO: Validate Required Income, accept only decimals
		txtRequiredIncome.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        if (!newValue) { //when focus lost
	            if(!txtRequiredIncome.getText().matches("264[2-9]|26[5-9][0-9]|2[7-9][0-9]{2}|[3-9][0-9]{3}|10000")){
	            	txtRequiredIncome.setText("");
	            }
	        }
	    });
		// TODO: Validate Monthly SSI, accept only decimals
		txtMonthlySSI.focusedProperty().addListener((arg0, oldValue, newValue) -> {
	        if (!newValue) { //when focus lost
	            if(!txtMonthlySSI.getText().matches("[0-9]|[0-9]{2}|[0-9]{3}|1[0-9]{3}|2[0-5][0-9]{2}|26[0-3][0-9]|264[0-2]")){
	            	txtMonthlySSI.setText("");
	            }
	        }

	    });
		
	}

	@FXML
	public void btnClear(ActionEvent event) {
		System.out.println("Clear pressed");

		// disable read-only controls
		txtSaveEachMonth.setDisable(true);
		txtWhatYouNeedToSave.setDisable(true);

		// Clear, enable txtYearsToWork
		txtYearsToWork.clear();
		txtYearsToWork.setDisable(false);

		// TODO: Clear, enable the rest of the input controls. Hint! You already have a
		// HashMap of all the input controls....!!!!
		
		txtAnnualReturnWorking.clear();
		txtAnnualReturnWorking.setDisable(false);

		txtAnnualReturnRetired.clear();
		txtAnnualReturnRetired.setDisable(false);
		
		txtMonthlySSI.clear();
		txtMonthlySSI.setDisable(false);
		
		txtRequiredIncome.clear();
		txtRequiredIncome.setDisable(false);
		
		txtYearsRetired.clear();
		txtYearsRetired.setDisable(false);
		
	}

	@FXML
	public void btnCalculate() {

		System.out.println("calculating");

		txtSaveEachMonth.setDisable(false);
		txtWhatYouNeedToSave.setDisable(false);

		// TODO: Calculate txtWhatYouNeedToSave value...
		// TODO: Then calculate txtSaveEachMonth, using amount from txtWhatYouNeedToSave
		// as input
		
		String annualReturnRetired = txtAnnualReturnRetired.getText();
		double dAnnualReturnRetired = Double.parseDouble(annualReturnRetired);
		
		String yearsToWork = txtYearsToWork.getText();
		int iYearsToWork = Integer.parseInt(yearsToWork);
		
		String monthlySSI = txtMonthlySSI.getText();
		int dMonthlySSI = Integer.parseInt(monthlySSI);
		
		String annualReturnWorking = txtAnnualReturnWorking.getText();
		double dAnnualReturnWorking = Double.parseDouble(annualReturnWorking);
		
		String requiredIncome = txtRequiredIncome.getText();
		int dRequiredIncome = Integer.parseInt(requiredIncome);
		
		String yearsRetired = txtYearsRetired.getText();
		int iYearsRetired = Integer.parseInt(yearsRetired);
		
		
		Retirement retirement1 = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired, dAnnualReturnRetired,
				dRequiredIncome, dMonthlySSI);
		txtWhatYouNeedToSave.setText(" " + retirement1.TotalAmountToSave());
		txtSaveEachMonth.setText(" " + retirement1.MonthlySavings());
	}
}
