package Date;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import District.Dnode;
import District.Dtree;
import Location.Lnode;
import Location.Lstack;
import Martyr.Martyr;
import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class MartyrPane {

	BorderPane root;
	VBox  Cvbox, Rvbox, Addvbox, Deletevbox, Updatevbox, navigateVbox, names, Tvbox, AddMvbox, ShowList, Bvbox;
	HBox Addhbox, Deletehbox, Updatehbox, Lhbox, Buttons, hbox1, hbox2, hbox3, LLHbox, AddMhbox, ShowMhbox;
	Label dname, lname, dateName, add, delete, update, error, l1, l11, l2, l21 ,l3 ,l31, addM, ShowM;
	Button addBtn, deleteBtn, updateBtn, nextBtn ,prevBtn, Load, ShowMBtn, addMBtn;
	DatePicker addTf,updateTf;
	ComboBox Dcombo, Lcombo;
	static ArrayList<String>Darr=new ArrayList<>();
	static ArrayList<String>Larr=new ArrayList<>();
	static ArrayList<Martyr>MarArr=new ArrayList<>();
	DateStack ptr=new DateStack();
	Lstack ptr1=new Lstack();
	Main main=new Main();
	Dtree dls;
	TableView<Martyr> table;
	@SuppressWarnings("unchecked")
	public MartyrPane(Dtree dls, String DisName, String locName) {
		this.dls=dls;
		dls.find(DisName).getlTree().find(locName).getDateTree().inOrder();

		l1=new Label("Average martyrs ages in that date :");
		l1.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l1.setTextFill(Color.web("#FFFFFF"));
		l11=new Label();
		l11.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l11.setTextFill(Color.web("#f59725"));
		l2=new Label("The youngest martyr in that date :");
		l2.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l2.setTextFill(Color.web("#FFFFFF"));
		l21=new Label();
		l21.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l21.setTextFill(Color.web("#f59725"));
		l3=new Label("The oldest martyrs in that date :");
		l3.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l3.setTextFill(Color.web("#FFFFFF"));
		l31=new Label();
		l31.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l31.setTextFill(Color.web("#f59725"));
		error=new Label();
		error.setTextFill(Color.RED);

		getDnames(dls.getRoot());
		Dcombo=new ComboBox(FXCollections.observableArrayList(Darr));
		Dcombo.getSelectionModel().select(DisName);
		getLnames(dls.find(DisName).getlTree().getRoot());
		Lcombo=new ComboBox(FXCollections.observableArrayList(Larr));
		Lcombo.getSelectionModel().select(locName);

		root=new BorderPane();
		//root.setStyle("-fx-background-color:#9097a3");
		root.setBackground(new Background(new BackgroundImage(new Image("img//background.jpg"),BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				BackgroundSize.DEFAULT)));
		Lhbox=new HBox();
		Cvbox=new VBox();
		Rvbox=new VBox();
		LLHbox=new HBox();
		Bvbox=new VBox();

		dname=new Label();
		lname=new Label();
		dateName=new Label();
		dname.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,30));
		dname.setPadding(new Insets(20));
		lname.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,30));
		lname.setTextFill(Color.GREEN);
		dateName.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,30));
		dateName.setTextFill(Color.GREEN);
		add=new Label("Add Date");
		delete=new Label("Delete Date");
		update=new Label("Update Date");
		add.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));
		delete.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));
		update.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));
		addM=new Label("Add Martyr");
		ShowM=new Label("Show List of martyr");
		addM.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));
		ShowM.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));

		addTf=new DatePicker();
		updateTf=new DatePicker();
		addTf.setEditable(false);
		updateTf.setEditable(false);

		addBtn=new Button(null,new ImageView(new Image("img\\add.png",30,30,false,false)));
		addBtn.setStyle("-fx-background-color:transparent");
		deleteBtn=new Button(null,new ImageView(new Image("img\\Delete.png",30,30,false,false)));
		deleteBtn.setStyle("-fx-background-color:transparent");
		updateBtn=new Button(null,new ImageView(new Image("img\\update.png",30,30,false,false)));
		updateBtn.setStyle("-fx-background-color:transparent");
		nextBtn=new Button(null,new ImageView(new Image("img\\next.png",30,30,false,false)));
		nextBtn.setStyle("-fx-background-color:transparent");
		prevBtn=new Button(null,new ImageView(new Image("img\\prev.png",30,30,false,false)));
		prevBtn.setStyle("-fx-background-color:transparent");
		Load=new Button(null,new ImageView(new Image("img\\load.png",30,30,false,false)));
		Load.setStyle("-fx-background-color:transparent");
		addMBtn=new Button(null,new ImageView(new Image("img\\addMar.png",30,30,false,false)));
		addMBtn.setStyle("-fx-background-color:transparent");
		ShowMBtn=new Button(null,new ImageView(new Image("img\\search.png",30,30,false,false)));
		ShowMBtn.setStyle("-fx-background-color:transparent");

		Addvbox=new VBox();
		Deletevbox=new VBox();
		Updatevbox=new VBox();
		AddMvbox=new VBox();
		ShowList=new VBox();
		navigateVbox=new VBox();
		names=new VBox();
		Tvbox=new VBox();
		Tvbox.setAlignment(Pos.CENTER);
		Tvbox.setSpacing(10);
		Tvbox.setPadding(new Insets(10));

		Addhbox=new HBox();
		Deletehbox=new HBox();
		Updatehbox=new HBox();
		AddMhbox=new HBox();
		ShowMhbox=new HBox();
		hbox1=new HBox();
		hbox2=new HBox();
		hbox3=new HBox();

		Addhbox.getChildren().addAll(addTf, addBtn);
		Addhbox.setAlignment(Pos.CENTER);
		Addhbox.setSpacing(10);
		Deletehbox.getChildren().addAll(deleteBtn);
		Deletehbox.setAlignment(Pos.CENTER);
		Deletehbox.setSpacing(10);
		Updatehbox.getChildren().addAll(updateTf, updateBtn);
		Updatehbox.setAlignment(Pos.CENTER);
		Updatehbox.setSpacing(10);
		Buttons=new HBox();

		AddMhbox.getChildren().addAll(addMBtn);
		AddMhbox.setAlignment(Pos.CENTER);
		AddMhbox.setSpacing(10);
		ShowMhbox.getChildren().addAll(ShowMBtn);
		ShowMhbox.setAlignment(Pos.CENTER);
		ShowMhbox.setSpacing(10);

		Addvbox.getChildren().addAll(add, Addhbox);
		Addvbox.setAlignment(Pos.CENTER);
		Addvbox.setSpacing(10);
		Deletevbox.getChildren().addAll(delete, Deletehbox);
		Deletevbox.setAlignment(Pos.CENTER);
		Deletevbox.setSpacing(10);
		Updatevbox.getChildren().addAll(update, Updatehbox);
		Updatevbox.setAlignment(Pos.CENTER);
		Updatevbox.setSpacing(10);
		names.getChildren().addAll(dname, lname, dateName);
		names.setAlignment(Pos.CENTER);
		names.setSpacing(10);
		names.setPadding(new Insets(5));

		AddMvbox.getChildren().addAll(addM, AddMhbox);
		AddMvbox.setAlignment(Pos.CENTER);
		AddMvbox.setSpacing(10);
		ShowList.getChildren().addAll(ShowM, ShowMhbox);
		ShowList.setAlignment(Pos.CENTER);
		ShowList.setSpacing(10);

		Lhbox.getChildren().addAll(Addvbox, Updatevbox, Deletevbox);
		Lhbox.setAlignment(Pos.CENTER);
		Lhbox.setSpacing(10);
		Lhbox.setPadding(new Insets(0,0,30,0));

		LLHbox.getChildren().addAll(AddMvbox, ShowList);
		LLHbox.setAlignment(Pos.CENTER);
		LLHbox.setSpacing(10);
		LLHbox.setPadding(new Insets(0,0,30,0));

		Buttons.getChildren().addAll(prevBtn, nextBtn);
		Buttons.setAlignment(Pos.CENTER);
		Buttons.setSpacing(20);	

		dname.setText(DisName);
		lname.setText(locName);
		l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().averageAge()));
		l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().averageAge()));

		dateName.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()));
		l11.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
		l21.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
		l31.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));


		hbox1.getChildren().addAll(l1, l11);
		hbox2.getChildren().addAll(l2, l21);
		hbox3.getChildren().addAll(l3, l31);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(15);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(15);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(15);
		navigateVbox.getChildren().addAll(hbox1, hbox2, hbox3);
		navigateVbox.setAlignment(Pos.CENTER);
		navigateVbox.setSpacing(10);

		Cvbox.getChildren().addAll(names,navigateVbox,Buttons);
		Cvbox.setAlignment(Pos.CENTER);
		Cvbox.setSpacing(10);

		Tvbox.getChildren().addAll(Dcombo, Lcombo);

		nextBtn.setOnAction(e -> {
			error.setText("");
			if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem())!=null) {
				ptr.push(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().pop());

				if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek()!=null) {
				}else {

					dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().inOrder();

				}
				if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek()!=null){
					if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()!=null) {
						try {
							dateName.setText((dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()));

							if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData())!=null){
								l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
								l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
								l31.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
							}


						}catch(NullPointerException m) {
							l11.setText("No Martyrs in this date");
							l21.setText("No Martyrs in this date");
							l31.setText("No Martyrs in this date");
						}
					}
				}else {
					l11.setText("No Martyrs in this date");
					l21.setText("No Martyrs in this date");
					l31.setText("No Martyrs in this date");
				}
			}else {
				error.setText("Choose loaction first");
			}

		});


		prevBtn.setOnAction(e -> {
			error.setText("");
			if(ptr.top!=-1) {
				try {
					dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().push(ptr.pop());
					if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek()!=null){
						dateName.setText((dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()));
						if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData())!=null){
							l11.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
							l21.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
							l31.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
						}
						else {
							l11.setText("No Dates");
							l21.setText("No Dates");
							l31.setText("No Dates");
						}
					}

				}catch(NullPointerException ex) {

				}


			}
		});


		Dcombo.setOnAction(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				error.setText("");
				if(Dcombo.getSelectionModel().getSelectedItem()!=null) {
					updateLCombo();

					if(Lcombo.getSelectionModel().isEmpty()) {
						Lcombo.getSelectionModel().select(0);
					}
					else {
						dname.setText((String)Dcombo.getSelectionModel().getSelectedItem());
						dateName.setText("No dates in This Location");
						l11.setText("0");
						l21.setText("No Martyrs");
						l31.setText("No Martyrs");
					}
				}
			}
		});

		Lcombo.setOnAction(e->{
			error.setText("");
			dname.setText((String)Dcombo.getSelectionModel().getSelectedItem());
			lname.setText((String)Lcombo.getSelectionModel().getSelectedItem());

			if(lname.getText()!=null) {
				if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem())!=null) {
					if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find(lname.getText())!=null){
						dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find(lname.getText()).getDateTree().getDateStack().clear(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find(lname.getText()).getDateTree().getDateStack());
						dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find(lname.getText()).getDateTree().inOrder();

						if(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek()!=null){
							dateName.setText((dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()));
							if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest()!=null) {
								l11.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
								l21.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
								l31.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
							}else {
								l21.setText("No Martyrs");
								l31.setText("No Martyrs");
							}
						}
						else {
							dateName.setText("No dates in This Location");
							l11.setText("0");
							l21.setText("No Martyrs");
							l31.setText("No Martyrs");
						}
					}
				}
			}

		});

		addBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				error.setText("");
				if(addTf.getValue()!=null) {
					if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree()!=null){
						String date="";
						date+=addTf.getValue().getMonth().getValue()+"/";
						date+=addTf.getValue().getDayOfMonth()+"/";
						date+=addTf.getValue().getYear();
						dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().insert(date);

						dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().clear(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack());
						ptr.clear(ptr);

						dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().inOrder();
						dateName.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()));
						if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()!=-1) {
							l11.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
							l21.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
							l31.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
							error.setText("");
						}else {
							l11.setText("0");
							l21.setText("No Martyrs");
							l31.setText("No Martyrs");
						}
					}			
					else {
						error.setText("Choose Loacation");
					}

				}	
				else {
					error.setText("Enter Valid Data");
				}	

			}

		});

		updateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				error.setText("");
				if(updateTf.getValue()!=null) {
					if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem())!=null) {
						String date="";
						date+=updateTf.getValue().getMonth().getValue()+"/";
						date+=updateTf.getValue().getDayOfMonth()+"/";
						date+=updateTf.getValue().getYear();
						System.out.println(date);
						dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().updateDate(dateName.getText(), date);

						dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().clear(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack());
						ptr.clear(ptr);

						dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().inOrder();
						dateName.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()));
						l11.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
						l21.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
						l31.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
						error.setText("");
					}
					else {
						error.setText("Enter Valid Data");
					}
				}else {
					error.setText("Choose Location");
				}
			}
		});

		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				error.setText("");
				if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem())!=null) {
					if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree()!=null) {
						dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().delete(dateName.getText());
						dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().clear(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack());
						ptr.clear(ptr);

						if(dls.find(dname.getText())!=null) {
							dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().inOrder();
							if(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek()!=null) {
								dateName.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()));
								l11.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
								l21.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
								l31.setText(String.valueOf(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().find(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().find((String)Lcombo.getSelectionModel().getSelectedItem()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
							}
							else {
								dateName.setText("No Dates in Locations");
								l11.setText("No Dates");
								l21.setText("No Dates");
								l31.setText("No Dates");
							}
						}
					}
				}
			}

		});

		addMBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				getAddMStage().show();
			}
		});

		ShowMBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				getShowStage().show();
			}
		});



		Bvbox.getChildren().addAll(Lhbox, LLHbox, error);
		Bvbox.setAlignment(Pos.CENTER);
		Bvbox.setSpacing(10);
		Bvbox.setPadding(new Insets(10));

		root.setTop(Tvbox);
		root.setCenter(Cvbox);
		root.setBottom(Bvbox);
	}


	public Stage getAddMStage() {
		Stage stage=new Stage();
		BorderPane root=new BorderPane();
		Label Mname=new Label("Enter Name of Martyr:");
		Label Mage=new Label("Enter the Age of Martyr:");
		Label Mdate=new Label("Enter the date of the Martyr:");
		Label Mgender=new Label("Enter the Gender of Martyr:");
		Label error=new Label();
		error.setTextFill(Color.RED);

		TextField tfName=new TextField();
		TextField tfAge=new TextField();
		DatePicker tfDate=new DatePicker();
		TextField tfGender=new TextField();

		tfDate.setEditable(false);

		Button addBtn=new Button(null,new ImageView(new Image("img\\addMar.png",50,50,false,false)));
		addBtn.setStyle("-fx-background-color:transparent");

		GridPane gpane=new GridPane();

		VBox vbox=new VBox();

		gpane.add(Mname, 0, 0);
		gpane.add(tfName, 1, 0);
		gpane.add(Mage, 0, 1);
		gpane.add(tfAge, 1, 1);
		gpane.add(Mdate, 0, 2);
		gpane.add(tfDate, 1, 2);
		gpane.add(Mgender, 0, 3);
		gpane.add(tfGender, 1, 3);

		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(15);
		gpane.setVgap(15);

		vbox.getChildren().addAll(gpane,addBtn, error);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);

		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					if(!tfName.getText().isBlank() && !tfAge.getText().isBlank() && !(tfDate.getValue()==null) && !tfGender.getText().isBlank()) {
						String date="";
						date+=tfDate.getValue().getMonth().getValue()+"/";
						date+=tfDate.getValue().getDayOfMonth()+"/";
						date+=tfDate.getValue().getYear();
						System.out.println(date);
						if(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(date)==null) {
							dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().insert(date);
						}

						dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(date).getMls()
						.addFirst(new Martyr(tfName.getText(),new DateNode(date),Integer.parseInt(tfAge.getText()),new Lnode(lname.getText()),new Dnode(dname.getText()),tfGender.getText()));	

						dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().clear(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack());
						ptr.clear(ptr);

						dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().inOrder();
						dateName.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()));

						l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
						l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
						l31.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
						error.setText("");
						stage.close();
					}
					else {
						error.setText("Enter Valid data");
					}
				}catch (Exception e) {
					error.setText("Enter Valid data");
				}


			}
		});

		root.setCenter(vbox);
		stage.setScene(new Scene(root,400,400));
		return stage;
	}

	public Stage getShowStage() {
		Stage stage=new Stage();
		BorderPane root=new BorderPane();
		HBox cHbox=new HBox();
		HBox THbox=new HBox();		
		HBox BHbox=new HBox();		
		VBox vbox=new VBox();
		VBox Bvbox=new VBox();
		Label search=new Label("Search for Martyr");
		TextField searchTf=new TextField();
		Button updatehBtn=new Button(null,new ImageView(new Image("img\\update.png",50,50,false,false)));
		updatehBtn.setStyle("-fx-background-color:transparent");
		Button deleteBtn=new Button(null,new ImageView(new Image("img\\deleteMar.png",50,50,false,false)));
		deleteBtn.setStyle("-fx-background-color:transparent");
		Button addBtn=new Button(null,new ImageView(new Image("img\\addMar.png",50,50,false,false)));
		addBtn.setStyle("-fx-background-color:transparent");
		table=new TableView<>();
		table.setEditable(true);

		TableColumn <Martyr,String>marNameCol=new TableColumn<Martyr,String>("Martyr name");
		marNameCol.setCellValueFactory(new PropertyValueFactory<>("mname"));
		marNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		marNameCol.setOnEditCommit(e->{
			Martyr m=e.getRowValue();
			m.setmName(e.getNewValue());
			l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
			l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
			l31.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
		});

		TableColumn <Martyr,Integer>marageCol=new TableColumn<Martyr,Integer>("Martyr Age");
		marageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		marageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		marageCol.setOnEditCommit(e->{
			Martyr m=e.getRowValue();
			l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
			l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
			l31.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
		});

		TableColumn <Martyr,String>mardateCol=new TableColumn<Martyr,String>("Martyr Date");
		mardateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

		TableColumn <Martyr,String>marLocCol=new TableColumn<Martyr,String>("Martyr Location");
		marLocCol.setCellValueFactory(new PropertyValueFactory<>("location"));

		TableColumn <Martyr,String>marDisCol=new TableColumn<Martyr,String>("Martyr District");
		marDisCol.setCellValueFactory(new PropertyValueFactory<>("district"));

		TableColumn <Martyr,String>marGenCol=new TableColumn<Martyr,String>("Martyr Gender");
		marGenCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
		marGenCol.setCellFactory(TextFieldTableCell.forTableColumn());
		marGenCol.setOnEditCommit(e->{
			Martyr m=e.getRowValue();
			m.setGender(e.getNewValue());
			l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
			l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
			l31.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
		});

		table.getColumns().addAll(marNameCol, marageCol, mardateCol, marLocCol, marDisCol, marGenCol);
		table.setColumnResizePolicy(table.CONSTRAINED_RESIZE_POLICY);

		getMartyrNames();
		table.setItems(FXCollections.observableArrayList(MarArr));
		table.setMaxHeight(600);
		table.setMinWidth(1050);

		THbox.getChildren().addAll(search, searchTf);
		THbox.setAlignment(Pos.CENTER);
		THbox.setSpacing(15);
		THbox.setPadding(new Insets(40,0,30,0));

		cHbox.getChildren().add(table);
		cHbox.setAlignment(Pos.CENTER);
		cHbox.setSpacing(15);

		BHbox.getChildren().addAll(addBtn, deleteBtn);
		BHbox.setAlignment(Pos.CENTER);
		BHbox.setSpacing(200);
		BHbox.setPadding(new Insets(30,0,30,0));

		vbox.getChildren().addAll(cHbox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);



		searchTf.setOnKeyTyped(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				table.getItems().clear();
				table.setItems(FXCollections.observableArrayList(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dateName.getText()).getMls().searchMartyr(searchTf.getText())));
			}
		});

		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				getShowAddStage().show();
			}
		});

		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(table.getSelectionModel().getSelectedItem()!=null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Please Confirm");
					alert.setHeaderText("Delete Martyr");
					alert.setContentText("Are you sure?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.isPresent() && result.get() == ButtonType.OK) {
						dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dateName.getText()).getMls().removeByData(table.getSelectionModel().getSelectedItem().getMname());
						table.getItems().remove(table.getSelectionModel().getSelectedIndex());
						table.getItems().clear();
						getMartyrNames();
						table.setItems(FXCollections.observableArrayList(MarArr));
						if(!MarArr.isEmpty()){
							if(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()!=-1) {
								l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
								l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
								l31.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));
							}
							else {
								l11.setText("0");
								l21.setText("No Martyrs");
								l31.setText("No Martyrs");
							}
						}					
						else {
							l11.setText("0");
							l21.setText("No Martyrs");
							l31.setText("No Martyrs");
						}				}
					if(result.isPresent() && result.get() == ButtonType.CANCEL) {

					}

				}
			}
		});


		Bvbox.getChildren().addAll(BHbox, error);
		Bvbox.setAlignment(Pos.CENTER);
		Bvbox.setSpacing(10);

		root.setTop(THbox);
		root.setCenter(vbox);
		root.setBottom(Bvbox);
		stage.setScene(new Scene(root,1200,600));
		return stage;
	}

	public Stage getShowAddStage(){
		Stage stage=new Stage();
		BorderPane root=new BorderPane();
		Label Mname=new Label("Enter Name of Martyr:");
		Label Mage=new Label("Enter the Age of Martyr:");
		Label Mgender=new Label("Enter the Gender of Martyr:");
		Label error=new Label();
		error.setTextFill(Color.RED);

		TextField tfName=new TextField();
		TextField tfAge=new TextField();
		TextField tfGender=new TextField();

		Button addBtn=new Button("Add");

		GridPane gpane=new GridPane();

		VBox vbox=new VBox();

		gpane.add(Mname, 0, 0);
		gpane.add(tfName, 1, 0);
		gpane.add(Mage, 0, 1);
		gpane.add(tfAge, 1, 1);
		gpane.add(Mgender, 0, 2);
		gpane.add(tfGender, 1, 2);

		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(15);
		gpane.setVgap(15);

		vbox.getChildren().addAll(gpane,addBtn,error);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);

		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					error.setText("");
					if(!tfName.getText().isBlank() && !tfAge.getText().isBlank() && !tfGender.getText().isBlank()) {
						if(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dateName.getText())==null) {
							dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().insert(dateName.getText());
						}

						dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dateName.getText()).getMls()
						.addFirst(new Martyr(tfName.getText(),new DateNode(dateName.getText()),Integer.parseInt(tfAge.getText()),new Lnode(lname.getText()),new Dnode(dname.getText()),tfGender.getText()));	

						dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().clear(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack());
						ptr.clear(ptr);

						dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().inOrder();
						dateName.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()));

						l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.getMartyrAverage()));
						l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrYoungest().getMname()));
						l31.setText(String.valueOf(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().getDateStack().peek().getData()).mls.martyrOldest().getMname()));

						table.getItems().clear();
						getMartyrNames();
						table.setItems(FXCollections.observableArrayList(MarArr));
						stage.close();
					}
					else {
						error.setText("Enter Valid Data");
					}
				}catch(Exception ex) {
					error.setText("Enter Valid Data");
				}
			}
		});

		root.setCenter(vbox);
		stage.setScene(new Scene(root,400,400));
		return stage;
	}

	public void getMartyrNames() {
		MarArr.clear();
		if(dls.find(dname.getText()).getlTree().find(lname.getText())!=null) {
			if(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dateName.getText())!=null) {
				MarArr=dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().find(dateName.getText()).getMls().p();
				MarArr.sort(Comparator.comparing(Martyr::getMname));
			}
		}
	}

	public void getDnames(Dnode root){
		if (root != null){
			getDnames(root.getLeft());
			Darr.add(root.getData());
			getDnames(root.getRight());
		}

	}

	public void updateDCombo() {
		Darr.clear();
		getDnames(dls.getRoot());
		Dcombo.getItems().clear();
		Dcombo.setItems(FXCollections.observableArrayList(Darr));
	}

	public void updateCombo() {
		Darr.clear();
		getDnames(dls.getRoot());
		Dcombo.getItems().clear();
		Dcombo.setItems(FXCollections.observableArrayList(Darr));
	}

	public void getLnames(Lnode root){
		if (root != null){
			getLnames(root.getLeft());
			Larr.add(root.getData());
			getLnames(root.getRight());
		}

	}

	public void updateLCombo(){
		Larr.clear();
		if(dls.find((String)Dcombo.getSelectionModel().getSelectedItem())!=null) {
			getLnames(dls.find((String)Dcombo.getSelectionModel().getSelectedItem()).getlTree().getRoot());
		}
		Lcombo.getItems().clear();
		Lcombo.setItems(FXCollections.observableArrayList(Larr));
		if(Larr.isEmpty()) {
			dateName.setText("No dates in This Location");
			l11.setText("0");
			l21.setText("No Martyrs");
			l31.setText("No Martyrs");
		}
	}





	public BorderPane getRoot() {
		return root;
	}
	public void setRoot(BorderPane root) {
		this.root = root;
	}
	public Label getDname() {
		return dname;
	}
	public void setDname(Label dname) {
		this.dname = dname;
	}
	public Label getL11() {
		return l11;
	}
	public void setL11(Label l11) {
		this.l11 = l11;
	}
	public Label getL21() {
		return l21;
	}
	public void setL21(Label l21) {
		this.l21 = l21;
	}

	public ComboBox getDcombo() {
		return Dcombo;
	}

	public void setDcombo(ComboBox dcombo) {
		Dcombo = dcombo;
	}

	public ComboBox getLcombo() {
		return Lcombo;
	}

	public void setLcombo(ComboBox lcombo) {
		Lcombo = lcombo;
	}

}
