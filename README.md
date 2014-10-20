Dynamically remove and add views in Android
===
This sample project achieves exactly the same functionality and look as the edit UI in the stock Android contact app (or "People"), where you can remove a row field view by pressing the "x" icon on the right, and add a row field view by pressing the "Add new" button at the bottom. 

The example also handles some visibility logic for better UX as in the stock app: If a row becomes empty, "add new" and "x" will disappear. 

Here is a demo video in action: [http://www.youtube.com/watch?v=4HeqyG6FDhQ](http://www.youtube.com/watch?v=4HeqyG6FDhQ). 

The state of the views is not saved for rotation. You'll need to work with onSaveInstanceState and onRestoreInstanceState. I left some notes in the code you can start with.

My original stackoverflow answer with some details: [link](http://stackoverflow.com/questions/3995215/add-and-remove-views-in-android-dynamically/13106445#13106445)

Key methods and classes used 
---
TextWatcher, setVisibility, inflate, addView, removeView, animateLayoutChanges



