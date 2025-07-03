# 🎨 Java Paint 2D — Geometric Drawing App

**Java Paint 2D** is a graphical drawing application developed in **Java** using object-oriented principles. The program allows users to draw and manipulate geometric shapes (circle, rectangle, triangle, point, line) through a graphical interface, with full support for **saving and loading drawings in JSON format**.

---

## ✨ Features

- 🟣 **Drawing primitives**: circle, rectangle, triangle, point, and line  
- 🖱️ **Interactive GUI**: create, move, delete and alter shapes  
- 💾 **Persistence**: save and load canvas content via `.json` files  
- 🧱 **Object-Oriented Design**: clear modeling of shapes and actions  
- 📦 **UML model included**: visual structure of the system (see `.uml` files)

---

## 📁 Project Structure

| Folder / File               | Description |
|-----------------------------|-------------|
| `App.java`                  | Main entry point of the application |
| `Gui.java`                  | Main graphical user interface logic |
| `PainelDesenho.java`        | Custom canvas for shape rendering and interaction |
| `EntradaDeDados.java`       | Handles user input for shape creation |
| `AlterandoFiguras.java`     | Logic for editing shapes |
| `formas/`, `circulo/`, ...  | Packages containing shape-specific logic |
| `PPersistencia/`            | Handles saving/loading using JSON |
| `desenho.json`              | Example of saved drawing |
| `.uml` / `.umlcd` files     | UML class and component diagrams |

---

## 🖥 How to Run

### Using BlueJ (recommended)

1. Open the folder as a BlueJ project  
2. Run the `App` class  
3. Use the GUI to start drawing and testing!

### Via terminal

```bash
javac *.java */*.java
java App
```

Ensure your Java version is 17 or later.

---

## 💡 Technologies Used

- Java (OOP)
- Java AWT & Swing (for GUI)
- JSON (for saving drawings)
- UML (for design documentation)

---

## 👥 Authors

Developed by:  
*João Pedro Figols, Júlia Gachido Schmidt, Leonardo Fajardo Grupioni*  
Course: Object-Oriented Programming and GUI Development — 🎓 Computer Science Undergraduate at PUC-SP

---

## 📄 License

This project is licensed for academic and personal use only.
