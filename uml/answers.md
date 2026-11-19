# TP 3 - Answers to the questions

(a) "left to right direction" changes the layout: actors go on the left and use cases are spread in columns toward the right. Without it, PlantUML draws top to bottom (actors on top, use cases below), which makes a tall and narrow diagram. It changes nothing about the model, only the drawing.
In "Customer --> Withdraw", the arrow is a longer arrow with an arrowhead pointing at Withdraw. It means navigability: the actor initiates the use case (the actor is the one who triggers it). "--" is a plain line, with no direction. Chapter 3 uses the plain association line (no arrowhead) between an actor and a use case, as in Figure 1, so the arrow is not Chapter 3 notation. Both are valid UML, but follow the course: plain line.

(b) No. PlantUML rendered the reversed relationship without any error or warning: the arrow just points the other way (Return ..> Suspend). The tool checks syntax only: that the text is well formed and that the elements exist. It does not check the meaning: whether an extend goes in the right direction (the extension use case points to the base use case), whether the condition makes sense, or whether the model matches the requirements. Only a human reader (or a stricter modeling tool with rules) can detect that error.

(c) Advantages: (1) you only describe the elements and relations; no time spent placing boxes and lines, and nothing overlaps by hand. (2) The diagram stays consistent and is regenerated after each change, so changing one name or adding an element reorganizes everything.
Drawbacks: (1) little control: the result may be ugly or confusing (crossing lines, a note far from its element) and the fix is trial and error with declaration order and direction hints. (2) Small changes can move everything, so two versions of a diagram can look very different even if the text changed by one line.

(d) Editing: PlantUML = typing text, fast for people who know the syntax, but the syntax must be learned and there is no drag and drop; Modelio = drag and drop and a palette, easy to start, slower for big changes.
Layout: PlantUML is automatic (little control); Modelio is manual (full control, but you spend time on it).
Version control: PlantUML files are text, so git diff, merge and review work well; Modelio models are XML/binary project files, hard to read in a diff and conflicts are hard to merge.
Consistency between diagrams: Modelio keeps one model behind all diagrams: renaming an element updates every diagram, and the tool can check some rules. In PlantUML each file is independent, so the same actor or use case is written again in each file and names can drift apart.

(e) The models then follow the history of the code: the same commit, branch and review can change code and models, so you can see which version of the model matches which version of the code. Nothing gets lost or forgotten in someone's personal folder, everyone in the team works from the same files, diffs show what changed in the design, and the documentation can be reviewed and updated together with the code (and rebuilt automatically if needed).

Part E, step 2, what git diff shows (observed when running this TP):
- use-cases.puml: a clean diff, one line changed: -usecase "Register a loan" as Lend / +usecase "Lend copies" as Lend.
- use-cases.png: only "Binary files differ" (the size changed from 74652 to 73814 bytes). You cannot see what changed in the image. This is why the .puml source is the real source of truth and the PNG is only a generated export.
