package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

import Date.DateNode;
import District.DistirctPane;
import District.Dnode;
import District.Dtree;
import Location.Lnode;
import Martyr.Martyr;
import Martyr.Mls;
import Martyr.Mnode;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	static Dtree dls=new Dtree();
	BorderPane root;
	static TabPane tabPane=new TabPane();
	File file=null;
	FileChooser fileCh;
	boolean isSaved=false;
	boolean isRead=false;
	@Override
	public void start(Stage primaryStage) {
		try {
			root=new BorderPane();

			Tab tab1=new Tab("Districts Screen");
			Tab tab2=new Tab("Locations Screen");
			Tab tab3=new Tab("Martyr Screen");

			tab1.setDisable(true);
			tab2.setDisable(true);
			tab3.setDisable(true);

			HBox hbox=new HBox();
			Label label=new Label("Choose File to Start");
			label.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,50));
			hbox.getChildren().addAll(label);
			hbox.setPadding(new Insets(0,0,100,0));
			hbox.setAlignment(Pos.CENTER);


			root.setBackground(new Background(new BackgroundImage(new Image("img//background.jpg"),BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.CENTER,
					BackgroundSize.DEFAULT)));

			root.setCenter(new ImageView(new Image("img//map.png")));
			root.setBottom(hbox);
			//tab1.setContent(a);

			tab1.setClosable(false);
			tab2.setClosable(false);
			tab3.setClosable(false);

			MenuBar bar=new MenuBar();
			Menu menu=new Menu("File");
			bar.getMenus().addAll(menu);

			MenuItem open=new MenuItem("Open");
			open.setGraphic(new ImageView(new Image("img\\import.png",20,20,false,false)));
			MenuItem save=new MenuItem("Save");
			save.setGraphic(new ImageView(new Image("img\\save.png",20,20,false,false)));
			MenuItem newWindow=new MenuItem("New");
			newWindow.setGraphic(new ImageView(new Image("img\\newWindow.png",20,20,false,false)));

			menu.getItems().addAll(open,save,newWindow);

			save.setDisable(true);

			open.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					fileCh=new FileChooser();
					file=fileCh.showOpenDialog(primaryStage);	

					if(file==null) {
						file=new File("");
						tab1.setDisable(true);
						tab2.setDisable(true);
						tab3.setDisable(true);
					}
					else {
						isRead=true;
						open.setDisable(true);
						tab1.setDisable(false);
						tab2.setDisable(false);
						tab3.setDisable(false);
						save.setDisable(false);

						try {
							read();
						} catch (Exception e) {
							label.setText("Choose CSV File");
							System.exit(0);
						}

						tabPane.tabMinWidthProperty().bind(root.widthProperty().divide(3.25));

						tab1.setStyle("-fx-background-color:transparent;");
						tab2.setStyle("-fx-background-color:transparent;");
						tab3.setStyle("-fx-background-color:transparent;");


						tabPane.getTabs().addAll(tab1,tab2,tab3);

						tab1.setStyle("-fx-background-color:#a2a2a2");
						tab2.setStyle("-fx-background-color:transparent");
						tab3.setStyle("-fx-background-color:transparent");

						tabPane.setStyle("-fx-background-color:#a2a2a2");


						DistirctPane dp=new DistirctPane(dls);

						tab1.setContent(dp.getRoot());
						tab2.setContent(dp.getLp().getRoot());
						tab3.setContent(dp.getLp().getMp().getRoot());


						tab2.setOnSelectionChanged(new EventHandler<Event>() {

							@Override
							public void handle(Event arg0) {
								if(tab2.isSelected()) {
									tab2.setStyle("-fx-background-color:#949494");
									tab1.setStyle("-fx-background-color:transparent");
									tab3.setStyle("-fx-background-color:transparent");
									dp.getLp().updateCombo();
									dp.getLp().getDname().setText(dp.getDname().getText());
									if(dls.find(dp.getDname().getText())!=null) {
										dls.find(dp.getDname().getText()).getlTree().getLstack().clear(dls.find(dp.getDname().getText()).getlTree().getLstack());
										dls.find(dp.getDname().getText()).getlTree().getTemps().clear(dls.find(dp.getDname().getText()).getlTree().getTemps());
										dp.getLp().getPtr().clear(dp.getLp().getPtr());
										if(dls.find(dp.getDname().getText()).getlTree()!=null) {
											dls.find(dp.getDname().getText()).getlTree().NavigateLevelOreder();

											dls.find(dp.getDname().getText()).getlTree().getTemps().push(dls.find(dp.getDname().getText()).getlTree().getLstack().peek());
											dp.getLp().getPtr().push(dls.find(dp.getDname().getText()).getlTree().getLstack().pop());
										}
										else {
											dp.getLp().getLname().setText("No Locations in this District");
											dp.getLp().getL11().setText(String.valueOf(0));
											dp.getLp().getL21().setText(String.valueOf(0));
											dp.getLp().getL31().setText(String.valueOf(0));
										}
										if(((Lnode)dls.find(dp.getDname().getText()).getlTree().getTemps().peek()!=null)){
											dp.getLp().getLname().setText(((Lnode)dls.find(dp.getDname().getText()).getlTree().getTemps().peek()).getData());	
											dp.getLp().getLname().setText(((Lnode)dls.find(dp.getDname().getText()).getlTree().getTemps().peek()).getData());
											if(dls.find(dp.getDname().getText()).getlTree().find(dp.getLp().getLname().getText()).getDateTree().isEmpty()==false) {
												dp.getLp().getL11().setText(String.valueOf(dls.find(dp.getDname().getText()).getlTree().find(dp.getLp().getLname().getText()).getDateTree().earliestDate()));
												dp.getLp().getL21().setText(String.valueOf(dls.find(dp.getDname().getText()).getlTree().find(dp.getLp().getLname().getText()).getDateTree().latestDate()));		
												dp.getLp().getL31().setText(String.valueOf(dls.find(dp.getDname().getText()).getlTree().find(dp.getLp().getLname().getText()).getDateTree().maxMartyrNum().getData()));

												dp.getLp().getCombo().getSelectionModel().select(dp.getDname().getText());
											}
										}
										else {
											dp.getLp().getLname().setText("No Locations in this District");
											dp.getLp().getL11().setText(String.valueOf(0));
											dp.getLp().getL21().setText(String.valueOf(0));
											dp.getLp().getL31().setText(String.valueOf(0));
											dp.getLp().getCombo().getSelectionModel().select(dp.getDname().getText());
										}
									}
									else {
										dp.getLp().getLname().setText("No Locations in District");
										dp.getLp().getL11().setText("No Martyrs");
										dp.getLp().getL21().setText("No Martyrs");
										dp.getLp().getL31().setText("No Martyrs");
									}
								}


							}
						});

						tab1.setOnSelectionChanged(new EventHandler<Event>() {
							@Override
							public void handle(Event arg0) {
								if(tab1.isSelected()) {
									tab1.setStyle("-fx-background-color:#a2a2a2");
									tab2.setStyle("-fx-background-color:transparent");
									tab3.setStyle("-fx-background-color:transparent");
								}
								if(dls.find(dp.getDname().getText())!=null) {
									dp.getL11().setText(String.valueOf(dls.find(dp.getDname().getText()).getlTree().totalLocation()));
									dp.getL21().setText(String.valueOf(dls.find(dp.getDname().getText()).getlTree().LocationstotalMartyr()));
								}
								else {
									dp.getL11().setText("0");
									dp.getL21().setText("0");
								}
							}
						});

						tab3.setOnSelectionChanged(new EventHandler<Event>() {
							@Override
							public void handle(Event arg0) {
								if(tab3.isSelected()) {
									tab3.setStyle("-fx-background-color:#888888");
									tab1.setStyle("-fx-background-color:transparent");
									tab2.setStyle("-fx-background-color:transparent");
								}
								dp.getLp().updateCombo();
								dp.getLp().getDname().setText(dp.getDname().getText());
								dp.getLp().getMp().updateDCombo();
								dp.getLp().getMp().getDcombo().getSelectionModel().select(dp.getDname().getText());
								dp.getLp().getMp().updateLCombo();
								dp.getLp().getMp().getLcombo().getSelectionModel().select(dp.getLp().getLname().getText());
							}
						});
						root.setCenter(tabPane);
						root.setBottom(null);
					}
				}

			});

			save.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					FileChooser fsave=new FileChooser();
					File f=fsave.showSaveDialog(primaryStage);
					if(f!=null) {
						isSaved=true;
						saveFile(f);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("File Saved");
						alert.setHeaderText("File Saved Correctly");
						alert.setContentText(f.getPath());
					}
				}
			});

			newWindow.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.close();
					start(new Stage());
				}
			});

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					if(isSaved==false && isRead) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Please Confirm");
						alert.setHeaderText("Save File");
						alert.setContentText("If you leave without saveing you may lose your data");

						Optional<ButtonType> result = alert.showAndWait();
						if (result.isPresent() && result.get() == ButtonType.OK) {
							primaryStage.close();
						}
						if(result.isPresent() && result.get() == ButtonType.CANCEL) {
							arg0.consume();
						}
					}
				}
			});

			root.setTop(bar);
			Scene scene=new Scene(root,900,650);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("img//map.png",30,30,false,false));
			primaryStage.setTitle("Data Structure Phase2 _ AbdAlrheem Yaseen _ 1220783");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}




	public static void main(String[] args) {
		launch(args);
	}

	public void SelectTab2() {
		tabPane.getSelectionModel().select(1);
	}

	public void SelectTab3() {
		tabPane.getSelectionModel().select(2);
	}

	public  void read() throws FileNotFoundException {

		Scanner input=new Scanner(file);

		input.nextLine();

		while(input.hasNextLine()) {
			String line=input.nextLine();
			String []tokens=line.split(",");

			if(tokens[2].equals("")) {
				tokens[2]="0";
			}

			Dnode Dtemp=dls.find(tokens[4]);

			if(Dtemp!=null) { 
				Lnode Ltemp=dls.find(tokens[4]).getlTree().find(tokens[3]);

				if(Ltemp!=null) {
					DateNode Datetemp=dls.find(tokens[4]).getlTree().find(tokens[3]).getDateTree().find(tokens[1]);
					if(Datetemp != null) {
						dls.find(tokens[4]).getlTree().find(tokens[3]).getDateTree().find(tokens[1]).getMls().addSort55(new Martyr(tokens[0],new DateNode(tokens[1]),Integer.parseInt((tokens[2])),Ltemp,Dtemp,tokens[5]));;
					}
					else {
						dls.find(tokens[4]).getlTree().find(tokens[3]).getDateTree().insert(tokens[1]);
						dls.find(tokens[4]).getlTree().find(tokens[3]).getDateTree().find(tokens[1]).getMls().addSort55(new Martyr(tokens[0],new DateNode(tokens[1]),Integer.parseInt((tokens[2])),Ltemp,Dtemp,tokens[5]));
					}
				}
				else {
					dls.find(tokens[4]).getlTree().insert(tokens[3]);
					dls.find(tokens[4]).getlTree().find(tokens[3]).getDateTree().insert(tokens[1]);
					dls.find(tokens[4]).getlTree().find(tokens[3]).getDateTree().find(tokens[1]).getMls().addSort55(new Martyr(tokens[0],new DateNode(tokens[1]),Integer.parseInt((tokens[2])),Ltemp,Dtemp,tokens[5]));
				}
			}
			else {
				dls.insert(tokens[4]);
				dls.find(tokens[4]).getlTree().insert(tokens[3]);
				dls.find(tokens[4]).getlTree().find(tokens[3]).getDateTree().insert(tokens[1]);
				dls.find(tokens[4]).getlTree().find(tokens[3]).getDateTree().find(tokens[1]).getMls().addSort55(new Martyr(tokens[0],new DateNode(tokens[1]),Integer.parseInt((tokens[2])),dls.find(tokens[4]).getlTree().find(tokens[3]),dls.find(tokens[4]),tokens[5]));
			}

		}
	}

	public void saveFile(File file) {
		Dnode node=dls.getRoot();
		DisSave(node,file);
	}

	private void DisSave(Dnode root,File file){
		if (root != null)
		{
			DisSave(root.getLeft(), file);
			LocSave(root.getlTree().getRoot(),file);
			DisSave(root.getRight(),file);
		}
	}

	private void LocSave(Lnode root,File file){
		if (root != null)
		{
			LocSave(root.getLeft(),file);
			DateSave(root.getDateTree().getRoot(),file);
			LocSave(root.getRight(),file);
		}
	}


	private void DateSave(DateNode root,File file){
		if (root != null)
		{
			DateSave(root.getLeft(), file);
			MartyrSave(root.getMls(),file);
			DateSave(root.getRight(),file);
		}
	}



	private void MartyrSave(Mls list,File file){
		try (PrintWriter pwrite =new PrintWriter(new FileWriter(file, true));){
			Mnode node=list.first;
			for(int i=0;i<list.getCount();i++) {
				pwrite.println(((Martyr)node.getData()).toString());
				node=node.next;
			}
		}catch (Exception s) {

		}

	}
}
