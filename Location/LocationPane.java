package Location;

import java.util.ArrayList;
import java.util.Optional;

import Date.MartyrPane;
import District.Dnode;
import District.Dtree;
import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class LocationPane {
	BorderPane root,top;
	VBox  Cvbox, Rvbox, Bvbox, Addvbox, Deletevbox, Updatevbox, navigateVbox, names, LoadVBox;
	HBox Addhbox, Deletehbox, Updatehbox, Lhbox, Buttons, hbox1, hbox2, hbox3;
	Label dname;
	Label lname;
	Label add;
	Label delete;
	Label update;
	Label error;
	Label Load;
	Button addBtn, deleteBtn, updateBtn, nextBtn ,prevBtn, LoadBtn;
	TextField addTf,updateTf;
	Lstack ptr=new Lstack();
	Label l1, l11, l2, l21, l3, l31;
	ComboBox combo;
	int counter=0;
	private static ArrayList <String>arr=new ArrayList<>();
	Dtree dls;
	MartyrPane mp;
	Main main=new Main();
	@SuppressWarnings("unchecked")
	public LocationPane(Dtree dls, String district) {
		this.dls=dls;

		dls.find(district).getlTree().NavigateLevelOreder();

		l1=new Label("The earliest date that has martyrs :");
		l2=new Label("The latest dates that has martyrs :");
		l3=new Label("The date that has the maximum number of martyrs :");
		l11=new Label();
		l21=new Label();
		l31=new Label();
		l1.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l1.setTextFill(Color.web("#FFFFFF"));
		l2.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l2.setTextFill(Color.web("#FFFFFF"));
		l3.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l3.setTextFill(Color.web("#FFFFFF"));
		l11.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l11.setTextFill(Color.web("#f59725"));
		l21.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l21.setTextFill(Color.web("#f59725"));
		l31.setFont(Font.font("Arial",FontWeight.BOLD,20));
		l31.setTextFill(Color.web("#f59725"));
		error=new Label();
		error.setTextFill(Color.RED);


		root=new BorderPane();
		//root.setStyle("-fx-background-color:#9097a3");
		root.setBackground(new Background(new BackgroundImage(new Image("img//background.jpg"),BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				BackgroundSize.DEFAULT)));
		top=new BorderPane();
		//top.setStyle("-fx-background-color:#9097a3");
		root.setBackground(new Background(new BackgroundImage(new Image("img//background.jpg"),BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				BackgroundSize.DEFAULT)));

		Lhbox=new HBox();
		Cvbox=new VBox();
		Rvbox=new VBox();
		LoadVBox=new VBox();
		getDnames(dls.getRoot());
		combo=new ComboBox(FXCollections.observableArrayList(getArr()));
		combo.getSelectionModel().select(district);
		combo.setPrefWidth(150);

		dname=new Label();
		lname=new Label();
		dname.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,40));
		lname.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,40));
		lname.setTextFill(Color.GREEN);

		add=new Label("Add Location");
		delete=new Label("Delete Location");
		update=new Label("Update Location");
		add.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));
		delete.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));
		update.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,20));
		Load=new Label("Load Martyr Screen");
		Load.setFont(Font.font("Arial",FontWeight.BOLD,20));

		addTf=new TextField();
		updateTf=new TextField();
		addTf.setStyle("-fx-border-radius:30");

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
		LoadBtn=new Button(null,new ImageView(new Image("img\\load.png",50,50,false,false)));
		LoadBtn.setStyle("-fx-background-color:transparent");


		Addvbox=new VBox();
		Deletevbox=new VBox();
		Updatevbox=new VBox();
		navigateVbox=new VBox();
		names=new VBox();
		Bvbox=new VBox();

		Addhbox=new HBox();
		Deletehbox=new HBox();
		Updatehbox=new HBox();
		hbox1=new HBox();
		hbox2=new HBox();
		hbox3=new HBox();

		Addhbox.getChildren().addAll(addTf, addBtn);
		Addhbox.setAlignment(Pos.CENTER);
		Addhbox.setSpacing(15);
		Deletehbox.getChildren().addAll(deleteBtn);
		Deletehbox.setAlignment(Pos.CENTER);
		Deletehbox.setSpacing(15);
		Updatehbox.getChildren().addAll(updateTf, updateBtn);
		Updatehbox.setAlignment(Pos.CENTER);
		Updatehbox.setSpacing(15);
		Buttons=new HBox();

		Addvbox.getChildren().addAll(add, Addhbox);
		Addvbox.setAlignment(Pos.CENTER);
		Addvbox.setSpacing(15);
		Deletevbox.getChildren().addAll(delete, Deletehbox);
		Deletevbox.setAlignment(Pos.CENTER);
		Deletevbox.setSpacing(15);
		Updatevbox.getChildren().addAll(update, Updatehbox);
		Updatevbox.setAlignment(Pos.CENTER);
		Updatevbox.setSpacing(15);
		LoadVBox.getChildren().addAll(Load, LoadBtn);
		LoadVBox.setAlignment(Pos.CENTER);
		LoadVBox.setSpacing(15);

		Lhbox.getChildren().addAll(LoadVBox, Addvbox, Updatevbox, Deletevbox);
		Lhbox.setAlignment(Pos.CENTER);
		Lhbox.setSpacing(15);
		Lhbox.setPadding(new Insets(0,0,50,0));

		Buttons.getChildren().addAll(prevBtn, nextBtn);
		Buttons.setAlignment(Pos.CENTER);
		Buttons.setSpacing(20);	

		hbox1.getChildren().addAll(l1,l11);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(15);
		hbox2.getChildren().addAll(l2,l21);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(15);
		hbox3.getChildren().addAll(l3,l31);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(15);

		navigateVbox.getChildren().addAll(hbox1, hbox2, hbox3);
		navigateVbox.setAlignment(Pos.CENTER);
		navigateVbox.setSpacing(15);

		names.getChildren().addAll(dname,lname);
		names.setAlignment(Pos.CENTER);
		names.setSpacing(15);
		names.setPadding(new Insets(20));

		Cvbox.getChildren().addAll(names,navigateVbox,Buttons);
		Cvbox.setAlignment(Pos.CENTER);
		Cvbox.setSpacing(15);
		//System.out.println(dls.find(district).getlTree().getRoot().data);

		dls.find(district).getlTree().getTemps().push(dls.find(district).getlTree().getLstack().peek());
		ptr.push(dls.find(district).getlTree().getLstack().pop());

		lname.setText(((Lnode)dls.find(district).getlTree().getTemps().peek()).data);	

		mp=new MartyrPane(dls, district, ((Lnode)dls.find(district).getlTree().getTemps().peek()).data);

		l11.setText(String.valueOf(dls.find(district).getlTree().find(getLname().getText()).dateTree.earliestDate()));
		l21.setText(String.valueOf(dls.find(district).getlTree().find(getLname().getText()).dateTree.latestDate()));		
		l31.setText(String.valueOf(dls.find(district).getlTree().find(getLname().getText()).dateTree.maxMartyrNum().getData()));
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				addTf.clear();
				updateTf.clear();
				error.setText("");
				counter++;
				dls.find(dname.getText()).getlTree().getTemps().push(dls.find(dname.getText()).getlTree().getLstack().peek());
				ptr.push(dls.find(dname.getText()).getlTree().getLstack().pop());

				if(dls.find((String)combo.getSelectionModel().getSelectedItem())!=null){

					if(((Lnode)dls.find(dname.getText()).getlTree().getTemps().peek()!=null)){
						lname.setText(((Lnode)dls.find(dname.getText()).getlTree().getTemps().peek()).data);
						if(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().isEmpty()==false) {
							getL11().setText(String.valueOf(dls.find(dname.getText()).getlTree().find(getLname().getText()).dateTree.earliestDate()));
							getL21().setText(String.valueOf(dls.find(dname.getText()).getlTree().find(getLname().getText()).dateTree.latestDate()));		
							getL31().setText(String.valueOf(dls.find(dname.getText()).getlTree().find(getLname().getText()).dateTree.maxMartyrNum().getData()));		
						}
					}
					else {
						l11.setText(String.valueOf(0));
						l21.setText(String.valueOf(0));
					}


				}
			}
		});

		prevBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				addTf.clear();
				updateTf.clear();
				error.setText("");
				if(counter !=0) {
					counter--;	
					dls.find(dname.getText()).getlTree().getLstack().push(dls.find(dname.getText()).getlTree().getTemps().pop());				

					if((dls.find(dname.getText()).getlTree().getTemps()).peek()!=null) {
						getLname().setText((dls.find(dname.getText()).getlTree().getTemps()).peek().getData()) ;
						if(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().isEmpty()==false) {
							getL11().setText(String.valueOf(dls.find(dname.getText()).getlTree().find(getLname().getText()).dateTree.earliestDate()));
							getL21().setText(String.valueOf(dls.find(dname.getText()).getlTree().find(getLname().getText()).dateTree.latestDate()));		
							getL31().setText(String.valueOf(dls.find(dname.getText()).getlTree().find(getLname().getText()).dateTree.maxMartyrNum().getData()));

						}
						else {
							lname.setText("No Location in this District");
							l11.setText("No dates");
							l21.setText(String.valueOf(0));
							l31.setText(String.valueOf(0));
						}
					}

				}

			}
		});

		combo.setOnAction(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				addTf.clear();
				updateTf.clear();
				error.setText("");
				if(!(dname.getText()==null)) {
					dname.setText((String)combo.getSelectionModel().getSelectedItem());
					if(combo.getSelectionModel().getSelectedItem()!=null) {
						dls.find(dname.getText()).getlTree().getLstack().clear(dls.find(dname.getText()).getlTree().getLstack());
						dls.find(dname.getText()).getlTree().getTemps().clear(dls.find(dname.getText()).getlTree().getTemps());
						ptr.clear(ptr);

						if(dls.find(getDname().getText()).getlTree().getRoot()!=null) {
							dls.find(getDname().getText()).getlTree().NavigateLevelOreder();

							dls.find(getDname().getText()).getlTree().getTemps().push(dls.find(getDname().getText()).getlTree().getLstack().peek());
							ptr.push(dls.find(getDname().getText()).getlTree().getLstack().pop());
						}else {
							lname.setText("No Location in this District");
							l11.setText("No dates");
							l21.setText(String.valueOf(0));
							l31.setText(String.valueOf(0));
						}

						if(((Lnode)dls.find(getDname().getText()).getlTree().getTemps().peek())!=null) {
							lname.setText(((Lnode)dls.find(getDname().getText()).getlTree().getTemps().peek()).getData());	
							l11.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().earliestDate()));
							l21.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().latestDate()));
							if(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().maxMartyrNum()!=null) {
								l31.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().maxMartyrNum().getData()));
							}
							else {
								l31.setText("0");
							}
						}

						else {
							lname.setText("No Location in this District");
							l11.setText("No dates");
							l21.setText(String.valueOf(0));
							l31.setText(String.valueOf(0));
						}
					}
				}
			}
		});

		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(!addTf.getText().isBlank()) {
					error.setText("");
					dls.find(dname.getText()).getlTree().insert(addTf.getText());
					dls.find(dname.getText()).getlTree().getLstack().clear(dls.find(dname.getText()).getlTree().getLstack());
					dls.find(dname.getText()).getlTree().getTemps().clear(dls.find(dname.getText()).getlTree().getTemps());
					ptr.clear(ptr);

					dls.find(getDname().getText()).getlTree().NavigateLevelOreder();

					dls.find(getDname().getText()).getlTree().getTemps().push(dls.find(getDname().getText()).getlTree().getLstack().peek());
					ptr.push(dls.find(getDname().getText()).getlTree().getLstack().pop());

					lname.setText(((Lnode)dls.find(getDname().getText()).getlTree().getTemps().peek()).getData());	
					if(dls.find(dname.getText()).getlTree().find(lname.getText()).getDateTree().isEmpty()==false) {
						l11.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().earliestDate()));
						l21.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().latestDate()));		
						l31.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().maxMartyrNum().getData()));
					}
					else {
						l11.setText("No dates");
					}
				}
				else {
					error.setText("Enter Valid Data");
				}
				addTf.clear();
				updateTf.clear();
			}
		});

		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Please Confirm");
				alert.setHeaderText("Delete Location");
				alert.setContentText("If you Delete This Location you will lose all district data");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					addTf.clear();
					updateTf.clear();
					error.setText("");
					if(dls.find(dname.getText())!=null) {
						dls.find(dname.getText()).getlTree().delete(lname.getText());
						dls.find(dname.getText()).getlTree().getLstack().clear(dls.find(dname.getText()).getlTree().getLstack());
						dls.find(dname.getText()).getlTree().getTemps().clear(dls.find(dname.getText()).getlTree().getTemps());
						ptr.clear(ptr);

						dls.find(getDname().getText()).getlTree().NavigateLevelOreder();

						dls.find(getDname().getText()).getlTree().getTemps().push(dls.find(getDname().getText()).getlTree().getLstack().peek());
						ptr.push(dls.find(getDname().getText()).getlTree().getLstack().pop());

						if(((Lnode)dls.find(getDname().getText()).getlTree().getTemps().peek())!=null) {
							lname.setText(((Lnode)dls.find(getDname().getText()).getlTree().getTemps().peek()).getData());
							l11.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().earliestDate()));
							l21.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().latestDate()));		
							if((dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().maxMartyrNum())!=null){
								l31.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().maxMartyrNum().getData()));
							}
						}
						else {
							lname.setText("No Locations in District");
							l11.setText("No Martyrs");
							l21.setText("No Martyrs");
							l31.setText("No Martyrs");				
						}
					}
				}
				if(result.isPresent() && result.get() == ButtonType.CANCEL) {
					addTf.clear();
					updateTf.clear();
				}
			}
		});

		updateBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(!(lname.getText().equals("No Locations in District"))) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Please Confirm");
					alert.setHeaderText("Update Location");
					alert.setContentText("Location name will be changed on updating");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.isPresent() && result.get() == ButtonType.OK) {
						if(!updateTf.getText().isBlank()) {
							error.setText("");
							dls.find(dname.getText()).getlTree().updateLocation(lname.getText(), updateTf.getText());
							dls.find(dname.getText()).getlTree().getLstack().clear(dls.find(dname.getText()).getlTree().getLstack());
							dls.find(dname.getText()).getlTree().getTemps().clear(dls.find(dname.getText()).getlTree().getTemps());
							ptr.clear(ptr);

							dls.find(getDname().getText()).getlTree().NavigateLevelOreder();

							dls.find(getDname().getText()).getlTree().getTemps().push(dls.find(getDname().getText()).getlTree().getLstack().peek());
							ptr.push(dls.find(getDname().getText()).getlTree().getLstack().pop());

							lname.setText(((Lnode)dls.find(getDname().getText()).getlTree().getTemps().peek()).getData());
							if(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().minValueNode()!=null) {
								l11.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().earliestDate()));
								l21.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().latestDate()));		
								l31.setText(String.valueOf(dls.find(getDname().getText()).getlTree().find(getLname().getText()).getDateTree().maxMartyrNum().getData()));
							}
						}

						else {
							error.setText("Enter Valid data");

						}
					}	
					if(result.isPresent() && result.get() == ButtonType.CANCEL) {
						addTf.clear();
						updateTf.clear();
					}

					addTf.clear();
					updateTf.clear();
				}
				else {
					error.setText("No Location Choosed to Update It");
					addTf.clear();
					updateTf.clear();
				}


			}
		});

		LoadBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				addTf.clear();
				updateTf.clear();
				main.SelectTab3();
			}
		});

		top.setCenter(combo);
		top.setPadding(new Insets(20));

		Bvbox.getChildren().addAll(Lhbox, error);
		Bvbox.setAlignment(Pos.CENTER);
		Bvbox.setSpacing(20);

		root.setTop(top);
		root.setCenter(Cvbox);
		root.setBottom(Bvbox);


	}

	public void getDnames(Dnode root){
		if (root != null){
			getDnames(root.getLeft());
			arr.add(root.getData());
			getDnames(root.getRight());
		}

	}

	public void updateCombo() {
		arr.clear();
		getDnames(dls.getRoot());
		combo.getItems().clear();
		combo.setItems(FXCollections.observableArrayList(arr));
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

	public Label getL31() {
		return l31;
	}

	public void setL31(Label l31) {
		this.l31 = l31;
	}

	public Label getLname() {
		return lname;
	}

	public void setLname(Label lname) {
		this.lname = lname;
	}

	public Lstack getPtr() {
		return ptr;
	}

	public void setPtr(Lstack ptr) {
		this.ptr = ptr;
	}

	public ComboBox getCombo() {
		return combo;
	}

	public void setCombo(ComboBox combo) {
		this.combo = combo;
	}

	public static ArrayList <String> getArr() {
		return arr;
	}

	public static void setArr(ArrayList <String> arr) {
		LocationPane.arr = arr;
	}

	public MartyrPane getMp() {
		return mp;
	}

	public void setMp(MartyrPane mp) {
		this.mp = mp;
	}



}
