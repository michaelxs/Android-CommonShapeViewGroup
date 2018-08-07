# CommonShapeViewGroup
[[点击查看中文版]](https://www.jianshu.com/p/4b20502f2692)<p>
To remove all shape files from the project, provide a generic shape style viewgroup.<p>
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16) [![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/michaelxs/Android-CommonShapeViewGroup/blob/master/LICENSE)<p>
![](https://github.com/michaelxs/Android-CommonShapeViewGroup/blob/master/screenshots/show.gif)
## Attribute
```xml
<declare-styleable name="CommonShapeViewGroup">
    <attr name="csvg_shapeMode" format="enum">
        <enum name="rectangle" value="0" />
        <enum name="oval" value="1" />
        <enum name="line" value="2" />
        <enum name="ring" value="3" />
    </attr>
    <attr name="csvg_fillColor" format="color" />
    <attr name="csvg_strokeColor" format="color" />
    <attr name="csvg_strokeWidth" format="dimension" />
    <attr name="csvg_cornerRadius" format="dimension" />
    <attr name="csvg_cornerPosition">
        <flag name="topLeft" value="1" />
        <flag name="topRight" value="2" />
        <flag name="bottomRight" value="4" />
        <flag name="bottomLeft" value="8" />
    </attr>
    <attr name="csvg_startColor" format="color" />
    <attr name="csvg_endColor" format="color" />
    <attr name="csvg_orientation" format="enum">
        <enum name="TOP_BOTTOM" value="0" />
        <enum name="LEFT_RIGHT" value="1" />
    </attr>
    <attr name="csvg_withElevation" format="boolean" />
</declare-styleable>
```
## Usage
ShapeRelativeLayout
```xml
<com.blue.view.ShapeRelativeLayout
    android:layout_width="200dp"
    android:layout_height="120dp"
    android:layout_margin="10dp"
    android:padding="10dp"
    app:csvg_cornerRadius="8dp"
    app:csvg_fillColor="#00bc71"
    app:csvg_withElevation="true">
  // child view
</com.blue.view.ShapeRelativeLayout>
```
ShapeRelativeLayout
```xml
<com.blue.view.ShapeConstraintLayout
    android:layout_width="200dp"
    android:layout_height="120dp"
    android:layout_margin="10dp"
    android:padding="10dp"
    app:csvg_cornerPosition="topRight|bottomRight"                                 
    app:csvg_cornerRadius="20dp"
    app:csvg_endColor="#00bc71"
    app:csvg_orientation="LEFT_RIGHT"
    app:csvg_startColor="#8800bc71">
  // child view
</com.blue.view.ShapeRelativeLayout>
```
## License
[Apache-2.0](https://opensource.org/licenses/Apache-2.0)
