package District;



import java.util.Optional;

import Location.Lnode;
import Location.LocationPane;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class DistirctPane {

	BorderPane root;
	VBox  Cvbox, Rvbox, Bvbox, Addvbox, Deletevbox, Updatevbox, navigateVbox, Loadvbox;
	HBox Addhbox, Deletehbox, Updatehbox, Lhbox, Buttons, hbox1, hbox2, Loadhbox;
	Label dname,add,delete,update, error, l1, l11, l2, l21, Loadlab;
	Button addBtn, deleteBtn, updateBtn, nextBtn ,prevBtn, Load;
	TextField addTf,updateTf;
	Dstack ptr=new Dstack();
	Main main=new Main();
	Image image;
	private LocationPane lp;
	public DistirctPane(Dtree dls) {

		dls.navigateInOrder();
		lp=new LocationPane(dls, ((Dnode)dls.dstack.peek()).data);
		l1=new Label("Total Number Of Loacations");
		l1.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l1.setTextFill(Color.web("#FFFFFF"));
		l11=new Label();
		l11.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l11.setTextFill(Color.web("#f59725"));
		l2=new Label("Total Number Of Martyr");
		l2.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l2.setTextFill(Color.web("#FFFFFF"));
		l21=new Label();
		l21.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l21.setTextFill(Color.web("#f59725"));
		error=new Label();
		error.setTextFill(Color.RED);

		root=new BorderPane();


		//		root.setStyle("-fx-background-color:#9097a3");
		root.setBackground(new Background(new BackgroundImage(new Image("img//background.jpg"),BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				BackgroundSize.DEFAULT)));

		Lhbox=new HBox();
		Cvbox=new VBox();
		Rvbox=new VBox();

		dname=new Label();
		dname.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,40));
		dname.setPadding(new Insets(20));
		add=new Label("Add District");
		delete=new Label("Delete District");
		update=new Label("Update District");
		Loadlab=new Label("Load the first Location");
		add.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));
		delete.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));
		update.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));
		Loadlab.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));

		addTf=new TextField();
		updateTf=new TextField();

		addBtn=new Button(null,new ImageView(new Image("img\\add.png",50,50,false,false)));
		addBtn.setStyle("-fx-background-color:transparent");
		deleteBtn=new Button(null,new ImageView(new Image("img\\Delete.png",50,50,false,false)));
		deleteBtn.setStyle("-fx-background-color:transparent");
		updateBtn=new Button(null,new ImageView(new Image("img\\update.png",50,50,false,false)));
		updateBtn.setStyle("-fx-background-color:transparent");
		nextBtn=new Button(null,new ImageView(new Image("img\\next.png",50,50,false,false)));
		nextBtn.setStyle("-fx-background-color:transparent");
		prevBtn=new Button(null,new ImageView(new Image("img\\prev.png",50,50,false,false)));
		prevBtn.setStyle("-fx-background-color:transparent");
		Load=new Button(null,new ImageView(new Image("img\\load.png",50,50,false,false)));
		Load.setStyle("-fx-background-color:transparent");

		Addvbox=new VBox();
		Deletevbox=new VBox();
		Updatevbox=new VBox();
		navigateVbox=new VBox();
		Loadvbox=new VBox();
		Bvbox=new VBox();

		Addhbox=new HBox();
		Deletehbox=new HBox();
		Updatehbox=new HBox();
		Loadhbox=new HBox();
		hbox1=new HBox();
		hbox2=new HBox();

		Addhbox.getChildren().addAll(addTf, addBtn);
		Addhbox.setAlignment(Pos.CENTER);
		Addhbox.setSpacing(15);
		Deletehbox.getChildren().addAll(deleteBtn);
		Deletehbox.setAlignment(Pos.CENTER);
		Deletehbox.setSpacing(15);
		Updatehbox.getChildren().addAll(updateTf, updateBtn);
		Updatehbox.setAlignment(Pos.CENTER);
		Updatehbox.setSpacing(15);
		Loadhbox.getChildren().addAll(Load);
		Loadhbox.setAlignment(Pos.CENTER);
		Loadhbox.setSpacing(15);
		Buttons=new HBox();

		Addvbox.getChildren().addAll(add, Addhbox);
		Addvbox.setAlignment(Pos.CENTER);
		Addvbox.setSpacing(15);
		Deletevbox.getChildren().addAll(delete, Deletehbox);
		Deletevbox.setAlignment(Pos.CENTER);
		Deletevbox.setSpacing(15);
		Updatevbox.getChildren().addAll(update, Updatehbox);
		Updatevbox.setAlignment(Pos.CENTER);
		Loadvbox.getChildren().addAll(Loadlab, Loadhbox);
		Loadvbox.setAlignment(Pos.CENTER);
		Loadvbox.setSpacing(15);
		Loadvbox.setSpacing(15);

		image=new Image("img//map.png");

		Lhbox.getChildren().addAll(Loadvbox, Addvbox, Updatevbox, Deletevbox);
		Lhbox.setAlignment(Pos.CENTER);
		Lhbox.setSpacing(15);
		Lhbox.setPadding(new Insets(0,0,50,0));

		dname.setText(((Dnode)dls.dstack.peek()).data);

		Buttons.getChildren().addAll(prevBtn, nextBtn);
		Buttons.setAlignment(Pos.CENTER);
		Buttons.setSpacing(20);	

		l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().totalLocation()));
		l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().LocationstotalMartyr()));

		hbox1.getChildren().addAll(l1, l11);
		hbox2.getChildren().addAll(l2, l21);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(15);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(15);
		navigateVbox.getChildren().addAll(hbox1, hbox2);
		navigateVbox.setAlignment(Pos.CENTER);
		navigateVbox.setSpacing(15);

		Cvbox.getChildren().addAll(dname,navigateVbox,Buttons);
		Cvbox.setAlignment(Pos.CENTER);
		Cvbox.setSpacing(15);
		Cvbox.setPadding(new Insets(0));

		nextBtn.setOnAction(e -> {
			addTf.clear();
			updateTf.clear();
			error.setText("");
			ptr.push(dls.getStack().pop());

			if(dls.getStack().peek()!=null) {
				dname.setText(dls.getStack().peek().getData()) ;
				l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().totalLocation()));
				l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().LocationstotalMartyr()));
				lp.getCombo().getSelectionModel().select(dname.getText());
				if(ptr.getTop()==0) {
				}
			}
		});

		prevBtn.setOnAction(e -> {
			addTf.clear();
			updateTf.clear();
			if(ptr.getTop()!=-1) {
				dls.getStack().push(ptr.pop());

				dname.setText(dls.getStack().peek().getData()) ;
				l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().totalLocation()));
				l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().LocationstotalMartyr()));
				lp.getCombo().getSelectionModel().select(dname.getText());
				error.setText("");
			}



		});


		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					if(!addTf.getText().isBlank()) {
						dls.insert(addTf.getText());
						dname.setText(addTf.getText());
						dls.getStack().clear(dls.getStack());
						ptr.clear(ptr);
						dls.navigateInOrder();
						dname.setText((dls.getStack()).peek().getData()) ;
						l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().totalLocation()));
						l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().LocationstotalMartyr()));
						lp.updateCombo();
						lp.getCombo().getSelectionModel().select(dname.getText());
						error.setText("");
						addTf.clear();
						updateTf.clear();
					}
					else {
						error.setText("Enter Valid Data");
					}
				}catch (Exception e) {

				}
			}
		});

		updateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Please Confirm");
				alert.setHeaderText("Update District");
				alert.setContentText("District name will be changed on updating");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {

					try {
						if(!updateTf.getText().isBlank()) {
							dls.updateDistrict(dname.getText(),updateTf.getText());
							dname.setText(updateTf.getText());
							dls.getStack().clear(dls.getStack());
							ptr.clear(ptr);
							dls.navigateInOrder();
							dname.setText((dls.getStack()).peek().getData()) ;
							l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().totalLocation()));
							l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().LocationstotalMartyr()));
							lp.updateCombo();
							lp.getCombo().getSelectionModel().select(dname.getText());
							error.setText("");
							addTf.clear();
							updateTf.clear();
						}
						else {
							error.setText("Enter valid Data");
						}
					}catch (Exception e) {
						error.setText("No District to update it");
					}
				}
				if(result.isPresent() && result.get() == ButtonType.CANCEL) {
					addTf.clear();
					updateTf.clear();
				}		
			}
		});

		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Please Confirm");
				alert.setHeaderText("Delete District");
				alert.setContentText("If you Delete This District you will lose all district data");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					addTf.clear();
					updateTf.clear();
					error.setText("");
					dls.delete(dname.getText());
					dls.getStack().clear(dls.getStack());
					ptr.clear(ptr);
					dls.navigateInOrder();
					if((dls.getStack()).peek()!=null) {
						dname.setText((dls.getStack()).peek().getData());

						l11.setText(String.valueOf(dls.find(dname.getText()).getlTree().totalLocation()));
						l21.setText(String.valueOf(dls.find(dname.getText()).getlTree().LocationstotalMartyr()));
					}
					else {
						dname.setText("There Are No DISTRICTS :)");
						l11.setText(String.valueOf(0));
						l21.setText(String.valueOf(0));
					}

					if(dls.find(getDname().getText())!=null) {
						dls.find(getDname().getText()).getlTree().getLstack().clear(dls.find(getDname().getText()).getlTree().getLstack());
						dls.find(getDname().getText()).getlTree().getTemps().clear(dls.find(getDname().getText()).getlTree().getTemps());
						getLp().getPtr().clear(getLp().getPtr());
						if(dls.find(getDname().getText()).getlTree()!=null) {
							dls.find(getDname().getText()).getlTree().NavigateLevelOreder();

							dls.find(getDname().getText()).getlTree().getTemps().push(dls.find(getDname().getText()).getlTree().getLstack().peek());
							getLp().getPtr().push(dls.find(getDname().getText()).getlTree().getLstack().pop());
						}
						else {
							getLp().getLname().setText("No Locations in this District");
							getLp().getL11().setText(String.valueOf(0));
							getLp().getL21().setText(String.valueOf(0));
							getLp().getL31().setText(String.valueOf(0));
						}
						if(((Lnode)dls.find(getDname().getText()).getlTree().getTemps().peek()!=null)){
							getLp().getLname().setText(((Lnode)dls.find(getDname().getText()).getlTree().getTemps().peek()).getData());	
							getLp().getLname().setText(((Lnode)dls.find(getDname().getText()).getlTree().getTemps().peek()).getData());
							if(dls.find(getDname().getText()).getlTree().find(getLp().getLname().getText()).getDateTree().isEmpty()==false) {
								getLp().getL11().setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLp().getLname().getText()).getDateTree().earliestDate()));
								getLp().getL21().setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLp().getLname().getText()).getDateTree().latestDate()));		
								getLp().getL31().setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLp().getLname().getText()).getDateTree().maxMartyrNum().getData()));

								getLp().getCombo().getSelectionModel().select(getDname().getText());
							}
						}
					}
				}
				if(result.isPresent() && result.get() == ButtonType.CANCEL) {
					addTf.clear();
					updateTf.clear();
				}	
			}

		});

		Load.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				addTf.clear();
				updateTf.clear();
				main.SelectTab2();
			}
		});

		Bvbox.getChildren().addAll(Lhbox, error);
		Bvbox.setAlignment(Pos.CENTER);
		Bvbox.setSpacing(20);

		BorderPane Croot=new BorderPane();
		Croot.setCenter(Cvbox);
		Croot.setPadding(new Insets(0,50,0,0));

		root.setCenter(Croot);
		root.setBottom(Bvbox);
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
	public LocationPane getLp() {
		return lp;
	}
	public void setLp(LocationPane lp) {
		this.lp = lp;
	}




}
