package com.example.datastructureprojectthree;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HelloApplication extends Application {
	File file1 = null;
	String s = "";
	AVL<Department> depDatabase = new AVL<>();

	public void saveData(AVL<Department> depDatabase) {

		File file = new File(file1.getPath());

		try {
			PrintWriter pw = new PrintWriter(file);

			pw.write(depDatabase.toStringInorder());

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	 private void traverseInOrder(TNode<Department> node) {
	        if (node != null) {
	            if (node.left != null)
	            	
	                traverseInOrder(node.left);
	            saveStudent(node.getData().getDep(), node.getData().getDepFilePath());
	            if (node.right != null)
	            
	                traverseInOrder(node.right);
	        }
	    }

	public void saveStudent(Hash_Quadratic<Student> dep, String fileName) {

		File file = new File(fileName);

		try {

			PrintWriter pw = new PrintWriter(file);
			pw.println(dep.printTable());
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void readDepartment(Stage pri) {

		Scanner sc = null;
		String[] parts = new String[2];
		try {

			FileChooser fileChooser = new FileChooser();
			file1 = fileChooser.showOpenDialog(pri);
			sc = new Scanner(file1);
			if (!file1.isFile()) {
				Alert alert = new Alert(Alert.AlertType.NONE, "Can not Read This File", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
				System.out.println("HHHHH");
				javafx.application.Platform.exit();
			} else

				while (sc.hasNextLine()) {
					parts = sc.nextLine().split("/");
					if (depDatabase.find(new Department(parts[0], parts[1])) == null) {

						Department d = new Department(parts[0].trim(), parts[1].trim());
						System.out.println("here");
						depDatabase.insert(d);
						File fi = new File(parts[1].trim());

						if (!fi.exists()) {
							fi.createNewFile();

						} else {

							readStudentDep(d);
						}
					}
				}

		} catch (FileNotFoundException e) {
			e.getMessage();
			System.out.println("1");
			Alert alert = new Alert(Alert.AlertType.NONE, "Can not Read This File", ButtonType.OK);
			if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			}
			javafx.application.Platform.exit();

		} catch (IOException e) {

			e.getMessage();
			System.out.println("2");
			Alert alert = new Alert(Alert.AlertType.NONE, "Can not Read This File", ButtonType.OK);
			if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			}
			javafx.application.Platform.exit();

		} catch (ArrayIndexOutOfBoundsException e) {
			e.getMessage();
			System.out.println("3");
			Alert alert = new Alert(Alert.AlertType.NONE, "Can not Read This File", ButtonType.OK);
			if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			}
			javafx.application.Platform.exit();

		} catch (NumberFormatException e) {
			System.out.println("4");
			e.getMessage();
			Alert alert = new Alert(Alert.AlertType.NONE, "The File Have been Read", ButtonType.OK);
			if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			}

		}
	}

	public boolean readStudentDep(Department d) {
		if (d != null) {
			try {
				File fr = new File(d.getDepFilePath());
				Scanner frs = new Scanner(fr);
				String[] u;
				if (fr.length() > 0) {
					try {
						while (frs.hasNextLine()) {
							u = frs.nextLine().split("/");
							if(d.getDep().search(new Student(u[0].trim(), 0, 0, null)) == null) {
							d.getDep().insert(new Student(u[0].trim(), Integer.parseInt(u[1].trim()),
									Double.parseDouble(u[2].trim()), u[3].trim()));
						}
					}
					} catch (Exception e) {
						System.out.println("Error");

					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Error");
			}
		}
		return false;
	}

	Scene one, Two, main, Printdep, Search, Insert, Delete, cal, PrintHash, TableSize, InsertRecord, DeleteRecord,
			SearchRecord, printFun;
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

	@Override
	public void start(Stage primaryStage) {
		try {
			try {
				readDepartment(primaryStage);

			} catch (NumberFormatException e) {
				Alert alert = new Alert(Alert.AlertType.NONE, "Can not Read This File", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
				System.out.println("5");
				javafx.application.Platform.exit();
				e.getMessage();
			} catch (NullPointerException e) {
				e.getMessage();
				Alert alert = new Alert(Alert.AlertType.NONE, "Can not Read This File", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
				System.out.println("6");
				javafx.application.Platform.exit();
			}
			StackPane st = new StackPane();
			Image mh = new Image("22.png");
			ImageView mah = new ImageView(mh);
			mah.setFitHeight(1050);
			mah.setFitWidth(1920);

			GridPane root = new GridPane();

			Button b1 = new Button("Department Menue");
			b1.setPrefWidth(165);
			b1.setPrefHeight(25);
			b1.setOnAction(e -> {
				primaryStage.setScene(one);

			});

			Button b2 = new Button("Student  Menue");
			b2.setPrefWidth(165);
			b2.setPrefHeight(25);
			b2.setOnAction(e -> {
				primaryStage.setScene(Two);

			});

			Button b3 = new Button("close");
			b3.setPrefWidth(165);
			b3.setPrefHeight(25);
			b3.setOnAction(e -> {
				javafx.application.Platform.exit();
			});

			root.setMargin(b1, new Insets(0, 0, 30, 0));
			root.setMargin(b3, new Insets(30, 0, 0, 0));

			root.add(b1, 0, 0);
			root.add(b2, 0, 1);
			root.add(b3, 0, 2);
			root.setAlignment(Pos.CENTER);
			st.getChildren().addAll(mah, root);

			main = new Scene(st, screenSize.getWidth(), screenSize.getHeight());
			main.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
//==============================================  Department ==================================================

			StackPane st1 = new StackPane();
			Image mh1 = new Image("22.png");
			ImageView mah1 = new ImageView(mh1);
			mah1.setFitHeight(1050);
			mah1.setFitWidth(1920);

			GridPane One = new GridPane();
			Button d1 = new Button("Print Department Sorted");
			d1.setPrefWidth(165);
			d1.setPrefHeight(25);

			d1.setOnAction(e -> {
				primaryStage.setScene(Printdep);
			});

			Button d2 = new Button("Search for Department");
			d2.setPrefWidth(165);
			d2.setPrefHeight(25);
			d2.setOnAction(e -> {
				primaryStage.setScene(Search);
			});
			Button d3 = new Button("insert a new Department ");
			d3.setPrefWidth(165);
			d3.setPrefHeight(25);
			d3.setOnAction(e -> {
				primaryStage.setScene(Insert);
			});
			Button d4 = new Button("Delete A Department ");
			d4.setPrefWidth(165);
			d4.setPrefHeight(25);

			d4.setOnAction(e -> {
				primaryStage.setScene(Delete);
			});

			Button d5 = new Button("Calcuate tree Height");
			d5.setPrefWidth(165);
			d5.setPrefHeight(25);
			d5.setOnAction(e -> {
				primaryStage.setScene(cal);
			});
			Button d6 = new Button("Back");
			d6.setPrefWidth(165);
			d6.setPrefHeight(25);
			d6.setOnAction(e -> {
				primaryStage.setScene(main);
			});

			One.setMargin(d1, new Insets(30, 0, 0, 0));
			One.setMargin(d2, new Insets(30, 0, 0, 0));
			One.setMargin(d3, new Insets(30, 0, 0, 0));
			One.setMargin(d4, new Insets(30, 0, 0, 0));
			One.setMargin(d5, new Insets(30, 0, 0, 0));
			One.setMargin(d6, new Insets(30, 0, 0, 0));

			One.add(d1, 0, 0);
			One.add(d2, 0, 1);
			One.add(d3, 0, 2);
			One.add(d4, 0, 3);
			One.add(d5, 0, 4);
			One.add(d6, 0, 5);
			One.setAlignment(Pos.CENTER);

			st1.getChildren().addAll(mah1, One);

			one = new Scene(st1, screenSize.getWidth(), screenSize.getHeight());
			one.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//============================================= Student==============================================

			StackPane st2 = new StackPane();
			Image mh2 = new Image("22.png");
			ImageView mah2 = new ImageView(mh2);
			mah2.setFitHeight(1050);
			mah2.setFitWidth(1920);

			GridPane two = new GridPane();
			Button s1 = new Button("Print Hashed table (including empty spots)");
			s1.setPrefWidth(165);
			s1.setPrefHeight(25);
			s1.setOnAction(e -> {
				primaryStage.setScene(PrintHash);
			});
			Button s2 = new Button("Print out Table size");
			s2.setPrefWidth(165);
			s2.setPrefHeight(25);
			s2.setOnAction(e -> {
				primaryStage.setScene(TableSize);
			});
			Button s3 = new Button("Print out used hash function");
			s3.setPrefWidth(165);
			s3.setPrefHeight(25);
			s3.setOnAction(e -> {
				primaryStage.setScene(printFun);
			});
			Button s4 = new Button("Insert A new record to hash Table");
			s4.setPrefWidth(165);
			s4.setPrefHeight(25);
			s4.setOnAction(e -> {
				primaryStage.setScene(InsertRecord);
			});
			Button s5 = new Button("Search for A Specific record ");
			s5.setPrefWidth(165);
			s5.setPrefHeight(25);
			s5.setOnAction(e -> {
				primaryStage.setScene(SearchRecord);

			});
			Button s6 = new Button("Delete A Specific record");
			s6.setPrefWidth(165);
			s6.setPrefHeight(25);
			s6.setOnAction(e -> {
				primaryStage.setScene(DeleteRecord);
			});
			Button s7 = new Button("Save hash table back to file");
			s7.setPrefWidth(165);
			s7.setPrefHeight(25);
			s7.setOnAction(e -> {
				traverseInOrder(depDatabase.root);
				saveData(depDatabase);
				Alert alert = new Alert(Alert.AlertType.NONE, "The Department have been Stored", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
			});
			Button s8 = new Button("Back");
			s8.setPrefWidth(165);
			s8.setPrefHeight(25);
			s8.setOnAction(e -> {
				primaryStage.setScene(main);
			});
			two.setMargin(s1, new Insets(30, 0, 0, 0));
			two.setMargin(s2, new Insets(30, 0, 0, 0));
			two.setMargin(s3, new Insets(30, 0, 0, 0));
			two.setMargin(s4, new Insets(30, 0, 0, 0));
			two.setMargin(s5, new Insets(30, 0, 0, 0));
			two.setMargin(s6, new Insets(30, 0, 0, 0));
			two.setMargin(s7, new Insets(30, 0, 0, 0));
			two.setMargin(s8, new Insets(30, 0, 0, 0));

			two.add(s1, 0, 0);
			two.add(s2, 0, 1);
			two.add(s3, 0, 2);
			two.add(s4, 0, 3);
			two.add(s5, 0, 4);
			two.add(s6, 0, 5);
			two.add(s7, 0, 6);
			two.add(s8, 0, 7);
			st2.getChildren().addAll(mah2, two);
			two.setAlignment(Pos.CENTER);

			Two = new Scene(st2, screenSize.getWidth(), screenSize.getHeight());
			Two.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//===================print Department sorted =============================================
			StackPane st3 = new StackPane();
			Image mh3 = new Image("22.png");
			ImageView mah3 = new ImageView(mh3);
			mah3.setFitHeight(1050);
			mah3.setFitWidth(1920);
			GridPane pri1 = new GridPane();

			Button pr2 = new Button("back");
			pr2.setOnAction(e -> {
				primaryStage.setScene(one);
			});
			TextArea Area = new TextArea();
			Button pr1 = new Button("Print");
			pr1.setPrefWidth(70);
			pr1.setPrefHeight(36);
			pr1.setOnAction(e -> {
				if (depDatabase.isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.NONE, "No Department Exit", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				} else

					Area.setText(depDatabase.toStringInorder());

			});
			pr2.setPrefWidth(70);
			pr2.setPrefHeight(36);

			pri1.add(Area, 1, 1);
			pri1.add(pr1, 2, 2);
			pri1.add(pr2, 0, 2);
			pri1.setAlignment(Pos.CENTER);
			pri1.setMargin(pr1, new Insets(30, 0, 0, 0));
			pri1.setMargin(pr2, new Insets(30, 0, 0, 0));
			st3.getChildren().addAll(mah3, pri1);

			Printdep = new Scene(st3, screenSize.getWidth(), screenSize.getHeight());
			Printdep.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//=================================Search Dep============================================
			StackPane st4 = new StackPane();
			Image mh4 = new Image("22.png");
			ImageView mah4 = new ImageView(mh4);
			mah4.setFitHeight(1050);
			mah4.setFitWidth(1920);

			GridPane Sea1 = new GridPane();
			TextField text1 = new TextField();
			text1.setPromptText(" Department Name :");
			TextArea Area1 = new TextArea();
			Button Se1 = new Button("Search");
			Se1.setOnAction(e -> {

				if (text1.getText().isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.NONE, "You Must Fill The Felid", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				} else {

					TNode<Department> m = depDatabase.find(new Department(text1.getText(), null));
					if (m == null) {
						Alert alert = new Alert(Alert.AlertType.NONE, "The Department Does Not Exit", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					} else {
						Alert alert = new Alert(Alert.AlertType.NONE, "The Department is Exitxting", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
						Area1.setText(m.getData().toString());
					}
				}
			});
			Button Se2 = new Button("Back");
			Se2.setOnAction(e -> {
				primaryStage.setScene(one);
			});

			Sea1.setMargin(text1, new Insets(0, 0, 30, 0));
			Sea1.setMargin(Se1, new Insets(30, 0, 0, 0));
			Sea1.setMargin(Se2, new Insets(30, 0, 0, 0));
			Sea1.add(text1, 0, 0);
			Sea1.add(Area1, 0, 1);
			Sea1.add(Se2, 1, 2);
			Sea1.add(Se1, 0, 2);
			Sea1.setAlignment(Pos.CENTER);
			st4.getChildren().addAll(mah4, Sea1);

			Search = new Scene(st4, screenSize.getWidth(), screenSize.getHeight());
			Search.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//============================================= Insert ==============================================================
			StackPane st5 = new StackPane();
			Image mh5 = new Image("22.png");
			ImageView mah5 = new ImageView(mh5);
			mah5.setFitHeight(1050);
			mah5.setFitWidth(1920);

			GridPane AddEditPane = new GridPane();

			Label Fliglab = new Label();
			Fliglab.setText("Department Name :");
			Fliglab.setPrefHeight(113);
			Fliglab.setPrefWidth(227);
			Fliglab.setFont(Font.font("Oranienbaum", 18));
			// Fliglab.setTextFill(Color.WHITE);
			AddEditPane.setMargin(Fliglab, new Insets(30, 0, 0, 130));

			Label Fliglab2 = new Label();
			Fliglab2.setText("Department Path :");
			Fliglab2.setPrefHeight(113);
			Fliglab2.setPrefWidth(227);
			Fliglab2.setFont(Font.font("Oranienbaum", 18));
			// Fliglab2.setTextFill(Color.WHITE);
			AddEditPane.setMargin(Fliglab2, new Insets(30, 0, 0, 130));

			TextField fligtext = new TextField();
			fligtext.setPrefHeight(25);
			fligtext.setPrefWidth(195);

			AddEditPane.setMargin(fligtext, new Insets(30, 0, 0, 0));

			TextField fligtext1 = new TextField();
			fligtext1.setPrefHeight(25);
			fligtext1.setPrefWidth(195);
			AddEditPane.setMargin(fligtext1, new Insets(30, 0, 0, 0));

			Button AddFligts = new Button("Add");
			AddFligts.setPrefHeight(61);
			AddFligts.setPrefWidth(106);
			AddFligts.setTextFill(Color.BLACK);
			AddFligts.setFont(Font.font("Oranienbaum", 18));
			AddFligts.setContentDisplay(ContentDisplay.TOP);
			AddFligts.setId("button");
			AddEditPane.setMargin(AddFligts, new Insets(30, 0, 0, 80));
			AddFligts.setOnAction(e -> {

				if (fligtext.getText().isEmpty() || fligtext1.getText().isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.NONE, "You Must Fill The Felid", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}

				} else {

					TNode<Department> m = depDatabase.find(new Department(fligtext.getText(), null));
					File f = new File(fligtext1.getText() + ".txt");
					if (m == null && !f.exists()) {

						depDatabase.insert(new Department(fligtext.getText(), fligtext1.getText() + ".txt"));
					
						Alert alert = new Alert(Alert.AlertType.NONE, "The Department has been Insert", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}

						Path paths = Paths.get(fligtext1.getText() + ".txt");

						String str = "";

						try {

							Files.writeString(paths, str, StandardCharsets.UTF_8);
						}

						catch (IOException ex) {
							System.out.print("Invalid Path");
						}

						fligtext.setText("");
						fligtext1.setText("");

					} else {
						Alert alert = new Alert(Alert.AlertType.NONE, "The Department is Existing", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					}
				}

			});

			Button backAdd = new Button("Back");
			backAdd.setPrefHeight(61);
			backAdd.setPrefWidth(106);
			backAdd.setTextFill(Color.BLACK);
			backAdd.setFont(Font.font("Oranienbaum", 18));
			backAdd.setContentDisplay(ContentDisplay.TOP);
			backAdd.setId("button");
			AddEditPane.setMargin(backAdd, new Insets(30, 0, 0, 150));
			backAdd.setOnAction(e -> {
				primaryStage.setScene(one);
			});

			AddEditPane.add(Fliglab, 0, 1);
			AddEditPane.add(Fliglab2, 0, 2);

			AddEditPane.add(fligtext, 1, 1);
			AddEditPane.add(fligtext1, 1, 2);

			AddEditPane.add(AddFligts, 1, 6);
			AddEditPane.add(backAdd, 0, 6);

			AddEditPane.setAlignment(Pos.CENTER);

			st5.getChildren().addAll(mah5, AddEditPane);

			Insert = new Scene(st5, screenSize.getWidth(), screenSize.getHeight());
			Insert.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//==================================================Delete ==========================

			StackPane st6 = new StackPane();
			Image mh6 = new Image("22.png");
			ImageView mah6 = new ImageView(mh6);
			mah6.setFitHeight(1050);
			mah6.setFitWidth(1920);

			GridPane displayPassengers = new GridPane();

			Label leb = new Label("Department Name:");
			leb.setPrefHeight(113);
			leb.setPrefWidth(227);
			leb.setFont(Font.font("Oranienbaum", 18));
			TextField tex = new TextField();
			tex.setPrefHeight(25);
			tex.setPrefWidth(195);

			Button printf1 = new Button("Delete");
			printf1.setPrefHeight(70);
			printf1.setPrefWidth(200);
			printf1.setTextFill(Color.BLACK);
			printf1.setFont(Font.font("Oranienbaum", 18));
			printf1.setContentDisplay(ContentDisplay.TOP);
			printf1.setId("button");
			displayPassengers.setMargin(printf1, new Insets(30, 0, 0, 150));
			printf1.setOnAction(e -> {

				try {

					if (tex.getText().isEmpty()) {
						Alert alert = new Alert(Alert.AlertType.NONE, "You Must Fill The Felid", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}

					} else {
						TNode<Department> m = depDatabase.find(new Department(tex.getText(), null));
						if (m != null) {

							for (int i = 0; i < depDatabase.count(); i++) {
								if (m.getData().getDepName().equals(tex.getText())) {

									Path path = FileSystems.getDefault()
											.getPath(m.getData().getDepFilePath().toString());
									try {
										Files.delete(path);
									} catch (NoSuchFileException x) {
										System.err.format("%s: no such" + " file or directory%n", path);
									} catch (IOException x) {
										System.err.println(x);
									}

									depDatabase.deletem(m.getData());

									Alert alert = new Alert(Alert.AlertType.NONE, "The Department had been Deleted",
											ButtonType.OK);
									if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
									}

									tex.setText("");

								}
							}

						} else {
							Alert alert = new Alert(Alert.AlertType.NONE, "Error ", ButtonType.OK);
							if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}
						}
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Alert alert = new Alert(Alert.AlertType.NONE, "You have been Delete The Root", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}

				}

			});

			Button back1 = new Button("Back");
			back1.setPrefHeight(70);
			back1.setPrefWidth(200);
			back1.setTextFill(Color.BLACK);
			back1.setFont(Font.font("Oranienbaum", 18));
			back1.setContentDisplay(ContentDisplay.TOP);
			back1.setId("button");
			displayPassengers.setMargin(back1, new Insets(30, 150, 0, 0));
			back1.setOnAction(e -> {
				primaryStage.setScene(one);
			});
			displayPassengers.add(leb, 0, 0);
			displayPassengers.add(tex, 1, 0);

			displayPassengers.add(printf1, 0, 1);
			displayPassengers.add(back1, 2, 1);

			displayPassengers.setAlignment(Pos.CENTER);
			st6.getChildren().addAll(mah6, displayPassengers);

			Delete = new Scene(st6, screenSize.getWidth(), screenSize.getHeight());
			Delete.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//=========================================calcaute Hights======================================
			StackPane st7 = new StackPane();
			Image mh7 = new Image("22.png");
			ImageView mah7 = new ImageView(mh7);
			mah7.setFitHeight(1050);
			mah7.setFitWidth(1920);

			GridPane Calu = new GridPane();

			Button printf2 = new Button("Calucalte");
			printf2.setPrefHeight(50);
			printf2.setPrefWidth(100);
			printf2.setTextFill(Color.BLACK);
			printf2.setFont(Font.font("Oranienbaum", 18));
			printf2.setContentDisplay(ContentDisplay.TOP);
			printf2.setId("button");
			Calu.setMargin(printf2, new Insets(0, 0, 0, 60));
			printf2.setOnAction(e -> {

				Alert alert = new Alert(Alert.AlertType.NONE,
						"The Higits is" + " " + depDatabase.heightAfterRebalance(), ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}

			});

			Button back2 = new Button("Back");
			back2.setPrefHeight(50);
			back2.setPrefWidth(100);
			back2.setTextFill(Color.BLACK);
			back2.setFont(Font.font("Oranienbaum", 18));
			back2.setContentDisplay(ContentDisplay.TOP);
			back2.setId("button");
			Calu.setMargin(back2, new Insets(30, 0, 0, 60));
			back2.setOnAction(e -> {
				primaryStage.setScene(one);
			});

			Calu.add(printf2, 1, 1);
			Calu.add(back2, 1, 2);

			Calu.setAlignment(Pos.CENTER);
			st7.getChildren().addAll(mah7, Calu);

			cal = new Scene(st7, screenSize.getWidth(), screenSize.getHeight());
			cal.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//======================================================Hashing===========================================================================

			StackPane st8 = new StackPane();
			Image mh8 = new Image("22.png");
			ImageView mah8 = new ImageView(mh8);
			mah8.setFitHeight(1050);
			mah8.setFitWidth(1920);

			GridPane printtable = new GridPane();

			Label leb3 = new Label("Enter the Department Name :");
			leb3.setPrefHeight(113);
			leb3.setPrefWidth(227);
			leb3.setFont(Font.font("Oranienbaum", 18));
			leb3.setTextFill(Color.WHITE);

			TextField tex4 = new TextField();
			tex4.setPrefHeight(25);
			tex4.setPrefWidth(195);

			TextArea Area5 = new TextArea();
			Area5.setPrefWidth(800);
			Area5.setPrefHeight(400);
			Area5.setEditable(false);
			Area5.setId("text-area");

			Button pr = new Button("Search & Display");
			pr.setPrefHeight(70);
			pr.setPrefWidth(200);
			pr.setTextFill(Color.BLACK);
			pr.setFont(Font.font("Oranienbaum", 18));
			pr.setContentDisplay(ContentDisplay.TOP);
			pr.setId("button");
			printtable.setMargin(pr, new Insets(30, 0, 0, 150));

			pr.setOnAction(e -> {
				if (tex4.getText().isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.NONE, "You Must Fill The Felid", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				} else {

					TNode<Department> mo = depDatabase.find(new Department(tex4.getText(), null));
					if (mo == null) {
						Alert alert = new Alert(Alert.AlertType.NONE, "No Departemt Exit", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					} else if (mo != null) {
						Alert alert = new Alert(Alert.AlertType.NONE, "The Departemt Found", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
						Area5.setText(mo.getData().getDep().toString());
					}

				}

			});

			Button back3 = new Button("Back");
			back3.setPrefHeight(70);
			back3.setPrefWidth(200);
			back3.setTextFill(Color.BLACK);
			back3.setFont(Font.font("Oranienbaum", 18));
			back3.setContentDisplay(ContentDisplay.TOP);
			back3.setId("button");
			printtable.setMargin(back3, new Insets(30, 150, 0, 0));
			back3.setOnAction(e -> {
				primaryStage.setScene(Two);
			});
			printtable.add(leb3, 0, 0);
			printtable.add(tex4, 1, 0);
			printtable.add(Area5, 1, 1);
			printtable.add(pr, 0, 2);
			printtable.add(back3, 2, 2);

			printtable.setAlignment(Pos.CENTER);
			st8.getChildren().addAll(mah8, printtable);

			PrintHash = new Scene(st8, screenSize.getWidth(), screenSize.getHeight());
			PrintHash.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//=================================================================================
			StackPane st9 = new StackPane();
			Image mh9 = new Image("22.png");
			ImageView mah9 = new ImageView(mh9);
			mah9.setFitHeight(1050);
			mah9.setFitWidth(1920);

			GridPane size = new GridPane();

			Label leb4 = new Label("Enter the Department Name :");
			leb4.setPrefHeight(113);
			leb4.setPrefWidth(227);
			leb4.setFont(Font.font("Oranienbaum", 18));
			leb4.setTextFill(Color.WHITE);

			TextField tex5 = new TextField();
			tex5.setPrefHeight(25);
			tex5.setPrefWidth(195);

			Button pri = new Button("Print");
			pri.setPrefHeight(70);
			pri.setPrefWidth(200);
			pri.setTextFill(Color.BLACK);
			pri.setFont(Font.font("Oranienbaum", 18));
			pri.setContentDisplay(ContentDisplay.TOP);
			pri.setId("button");
			size.setMargin(pri, new Insets(30, 0, 0, 150));

			pri.setOnAction(e -> {
				if (tex5.getText().isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.NONE, "You Must Fill The Felid", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				} else {

					TNode<Department> mo = depDatabase.find(new Department(tex5.getText(), null));
					if (mo == null) {
						Alert alert = new Alert(Alert.AlertType.NONE, "No Departemt Exit", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					} else if (mo != null) {
						Alert alert = new Alert(Alert.AlertType.NONE,
								"The Departemt Capacity is :" + mo.getData().getDep().getTableSize() + "\n"
										+ "The Student Size :" + mo.getData().getDep().getCurrentSize(),
								ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}

					}
				}

			});

			Button backr = new Button("Back");
			backr.setPrefHeight(70);
			backr.setPrefWidth(200);
			backr.setTextFill(Color.BLACK);
			backr.setFont(Font.font("Oranienbaum", 18));
			backr.setContentDisplay(ContentDisplay.TOP);
			backr.setId("button");
			size.setMargin(backr, new Insets(30, 150, 0, 0));
			backr.setOnAction(e -> {
				primaryStage.setScene(Two);
			});
			size.add(leb4, 0, 0);
			size.add(tex5, 1, 0);

			size.add(pri, 0, 2);
			size.add(backr, 2, 2);

			size.setAlignment(Pos.CENTER);
			st9.getChildren().addAll(mah9, size);

			TableSize = new Scene(st9, screenSize.getWidth(), screenSize.getHeight());
			TableSize.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//=================================== insertr===================
			StackPane st10 = new StackPane();
			Image mh10 = new Image("22.png");
			ImageView mah10 = new ImageView(mh10);
			mah10.setFitHeight(1050);
			mah10.setFitWidth(1920);

			GridPane editGrid = new GridPane();

			Label studentNameLabel = new Label("Student Name : ");
			Label studentIdLabel = new Label("Student ID : ");
			Label studentAvgLabel = new Label("Average : ");
			Label studentGenderLabel = new Label("Gender : ");
			Label studentDepLabel = new Label("Department Name : ");

			TextField studentNameText = new TextField();
			TextField studentIdText = new TextField();
			studentIdText.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
			TextField studentAvgText = new TextField();
			studentAvgText.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
			TextField studentGenderText = new TextField();
			TextField studentDepText = new TextField();

			RadioButton maleButton = new RadioButton("Male");
			RadioButton FemaleButton = new RadioButton("Female");

			ToggleGroup tg = new ToggleGroup();
			maleButton.setToggleGroup(tg);
			FemaleButton.setToggleGroup(tg);

			Button addButton = new Button("Insert");
			addButton.setPrefWidth(106);
			addButton.setPrefHeight(61);
			addButton.setOnAction(e -> {
				try {
					RadioButton sel = (RadioButton) tg.getSelectedToggle();
					String val = sel.getText();

					if (studentNameText.getText().isEmpty() || studentIdText.getText().isEmpty()
							|| studentAvgText.getText().isEmpty() || val.isEmpty()) {
						Alert alert = new Alert(Alert.AlertType.NONE, "You Must Fill The Felid", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					} else {

						TNode<Department> mo = depDatabase.find(new Department(studentDepText.getText(), null));
						if (mo == null) {
							Alert alert = new Alert(Alert.AlertType.NONE, "No Departemt Exit", ButtonType.OK);
							if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}
						} else if (mo != null) {
							mo.getData().getDep()
									.insert(new Student(studentNameText.getText(),
											Integer.parseInt(studentIdText.getText()),
											Double.parseDouble(studentAvgText.getText()), val));
						//	saveStudent(mo.getData().getDep(), mo.getData().getDepFilePath());

							Alert alert = new Alert(Alert.AlertType.NONE, "The Student has been Add ", ButtonType.OK);
							if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}
						}

					}
				} catch (NullPointerException m) {
					m.getMessage();
					Alert alert = new Alert(Alert.AlertType.NONE, "You Must Fill The Felid", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				}

			});

			Button inserPageBack = new Button("Back");
			inserPageBack.setPrefWidth(106);
			inserPageBack.setPrefHeight(61);
			inserPageBack.setOnAction(e -> {
				primaryStage.setScene(Two);
				studentNameText.setText("");
				studentIdText.setText("");
				studentAvgText.setText("");
				studentGenderText.setText("");
			});

			editGrid.add(studentNameLabel, 0, 0);
			editGrid.add(studentIdLabel, 0, 1);
			editGrid.add(studentAvgLabel, 0, 2);
			editGrid.add(studentGenderLabel, 0, 3);
			editGrid.add(studentDepLabel, 0, 4);

			editGrid.add(studentNameText, 1, 0);
			editGrid.add(studentIdText, 1, 1);
			editGrid.add(studentAvgText, 1, 2);
			editGrid.add(studentDepText, 1, 4);

			editGrid.add(inserPageBack, 1, 5);
			editGrid.add(addButton, 0, 5);
			editGrid.add(maleButton, 1, 3);
			editGrid.add(FemaleButton, 2, 3);

			editGrid.setAlignment(Pos.CENTER);
			st10.getChildren().addAll(mah10, editGrid);

			InsertRecord = new Scene(st10, screenSize.getWidth(), screenSize.getHeight());

			InsertRecord.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//===================================================Search=========================================

			StackPane st11 = new StackPane();
			Image mh11 = new Image("22.png");
			ImageView mah11 = new ImageView(mh11);
			mah11.setFitHeight(1050);
			mah11.setFitWidth(1920);

			GridPane editGrid1 = new GridPane();

			Label studentNameLabel1 = new Label("Student Name : ");
			Label studentIdLabel1 = new Label("Student ID : ");
			Label studentAvgLabel1 = new Label("Average : ");
			Label studentGenderLabel1 = new Label("Gender : ");
			Label studentDepLabel1 = new Label("Department Name : ");

			TextField studentNameText1 = new TextField();
			TextField studentIdText1 = new TextField();
			studentIdText1.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
			TextField studentAvgText1 = new TextField();
			studentAvgText1.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
			TextField studentGenderText1 = new TextField();
			TextField studentDepText1 = new TextField();

			RadioButton maleButton1 = new RadioButton("Male");
			RadioButton FemaleButton1 = new RadioButton("Female");

			ToggleGroup tg1 = new ToggleGroup();
			maleButton1.setToggleGroup(tg);
			FemaleButton1.setToggleGroup(tg);

			Button addButton1 = new Button("Search");
			addButton1.setPrefWidth(106);
			addButton1.setPrefHeight(61);
			studentIdText1.setEditable(false);
			studentAvgText1.setEditable(false);

			addButton1.setOnAction(e -> {
				try {

					if (studentDepText1.getText().isEmpty() || studentNameText1.getText().isEmpty()) {
						Alert alert = new Alert(Alert.AlertType.NONE, "You Muat Fill The Felid ", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					} else {

						TNode<Department> mo = depDatabase.find(new Department(studentDepText1.getText(), null));
						Student mo1 = mo.getData().getDep().search(new Student(studentNameText1.getText(), 0, 0, null));
						HNode<Student> mo2 = mo.getData().getDep()
								.searchF(new Student(studentNameText1.getText(), 0, 0, null));

						if (mo != null && mo1 != null && mo2.getF() == 'F') {
							Alert alert = new Alert(Alert.AlertType.NONE, "The Student has been found ", ButtonType.OK);
							if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}

							studentIdText1.setText(String.valueOf(mo1.getId()));
							studentAvgText1.setText(String.valueOf(mo1.getAverage()));
							if (mo1.getGender().equals("Female") || mo1.getGender().equals("F")) {
								FemaleButton1.setSelected(true);
							} else if (mo1.getGender().equals("Male") || mo1.getGender().equals("M")) {
								maleButton1.setSelected(true);
							}

						} else {
							Alert alert1 = new Alert(Alert.AlertType.NONE, "No Student Exit", ButtonType.OK);
							if (alert1.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
							}
						}

					}
				} catch (NullPointerException m) {
					m.getMessage();
					Alert alert1 = new Alert(Alert.AlertType.NONE, "No Student Exit", ButtonType.OK);
					if (alert1.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				}
			});

			Button inserPageBack1 = new Button("Back");
			inserPageBack1.setPrefWidth(106);
			inserPageBack1.setPrefHeight(61);
			inserPageBack1.setOnAction(e -> {
				primaryStage.setScene(Two);
				studentNameText1.setText("");
				studentIdText1.setText("");
				studentAvgText1.setText("");
				studentGenderText1.setText("");
			});

			editGrid1.add(studentNameLabel1, 0, 0);
			editGrid1.add(studentIdLabel1, 0, 1);
			editGrid1.add(studentAvgLabel1, 0, 2);
			editGrid1.add(studentGenderLabel1, 0, 3);
			editGrid1.add(studentDepLabel1, 0, 4);

			editGrid1.add(studentNameText1, 1, 0);
			editGrid1.add(studentIdText1, 1, 1);
			editGrid1.add(studentAvgText1, 1, 2);
			editGrid1.add(studentDepText1, 1, 4);

			editGrid1.add(inserPageBack1, 1, 5);
			editGrid1.add(addButton1, 0, 5);
			editGrid1.add(maleButton1, 1, 3);
			editGrid1.add(FemaleButton1, 2, 3);

			editGrid1.setAlignment(Pos.CENTER);

			st11.getChildren().addAll(mah11, editGrid1);

			SearchRecord = new Scene(st11, screenSize.getWidth(), screenSize.getHeight());
			SearchRecord.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

//===================================================Delete =============================

			StackPane st12 = new StackPane();
			Image mh12 = new Image("22.png");
			ImageView mah12 = new ImageView(mh12);
			mah12.setFitHeight(1050);
			mah12.setFitWidth(1920);

			GridPane editGrid2 = new GridPane();

			Label studentNameLabel2 = new Label("Student Name : ");

			Label studentDepLabel2 = new Label("Department Name : ");

			TextField studentNameText2 = new TextField();

			TextField studentDepText2 = new TextField();

			Button addButton2 = new Button("Delete");
			addButton2.setPrefWidth(106);
			addButton2.setPrefHeight(61);
			addButton2.setOnAction(e -> {

				if (studentNameText2.getText().isEmpty() || studentDepText2.getText().isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.NONE, "You Muat Fill The Felid ", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				} else {

					TNode<Department> mo = depDatabase.find(new Department(studentDepText2.getText(), null));
					Student mo1 = mo.getData().getDep().search(new Student(studentNameText2.getText(), 0, 0, null));

					if (mo != null && mo1 != null) {
						Alert alert = new Alert(Alert.AlertType.NONE, "The Student has been Deleted ", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
						mo.getData().getDep().delete(mo1);
					//	saveStudent(mo.getData().getDep(), mo.getData().getDepFilePath());

					} else {
						Alert alert1 = new Alert(Alert.AlertType.NONE, "No Student Exit", ButtonType.OK);
						if (alert1.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					}

				}
			});

			Button inserPageBack2 = new Button("Back");
			inserPageBack2.setPrefWidth(106);
			inserPageBack2.setPrefHeight(61);
			inserPageBack2.setOnAction(e -> {
				primaryStage.setScene(Two);
				studentNameText2.setText("");
				studentDepText2.setText("");
			});

			editGrid2.add(studentNameLabel2, 0, 0);

			editGrid2.add(studentDepLabel2, 0, 1);

			editGrid2.add(studentNameText2, 1, 0);

			editGrid2.add(studentDepText2, 1, 1);

			editGrid2.add(inserPageBack2, 1, 3);
			editGrid2.add(addButton2, 0, 3);

			editGrid2.setAlignment(Pos.CENTER);

			st12.getChildren().addAll(mah12, editGrid2);

			DeleteRecord = new Scene(st12, screenSize.getWidth(), screenSize.getHeight());

			DeleteRecord.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// ============================================Print hashfUNCTION
			// ===========================
			StackPane st13 = new StackPane();
			Image mh13 = new Image("22.png");
			ImageView mah13 = new ImageView(mh13);
			mah13.setFitHeight(1050);
			mah13.setFitWidth(1920);

			GridPane hash = new GridPane();

			Button pr2b = new Button("back");
			pr2b.setOnAction(e -> {
				primaryStage.setScene(Two);
			});
			TextArea Areab = new TextArea();
			Areab.setEditable(false);
			Button pr1b = new Button("Print");
			pr1b.setPrefWidth(70);
			pr1b.setPrefHeight(36);
			pr1b.setOnAction(e -> {
				Areab.setText("@Override \r\n" + "	public int hashCode() {\r\n" + "		int hash = 0;\r\n"
						+ "		for (int i = 0; i < name.length(); i++) {\r\n"
						+ "			hash = hash + name.charAt(i);\r\n" + "		}\r\n" + "		return hash;\r\n"
						+ "	}\r\n" + "	\r\n" + "");

			});
			pr2b.setPrefWidth(70);
			pr2b.setPrefHeight(36);

			hash.add(Areab, 1, 1);
			hash.add(pr1b, 2, 2);
			hash.add(pr2b, 0, 2);
			hash.setAlignment(Pos.CENTER);
			hash.setMargin(pr1b, new Insets(30, 0, 0, 0));
			hash.setMargin(pr2b, new Insets(30, 0, 0, 0));

			st13.getChildren().addAll(mah13, hash);

			printFun = new Scene(st13, screenSize.getWidth(), screenSize.getHeight());
			printFun.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(main);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
}
