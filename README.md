# CommonShapeViewGroup
[[点击查看中文版]](https://www.jianshu.com/p/fdcf38516a61)<p>
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
RelativeLayout
```xml
<com.blue.view.ShapeRelativeLayout
    android:layout_width="200dp"
    android:layout_height="120dp"
    app:csvg_cornerRadius="8dp"
    app:csvg_fillColor="#00bc71"
    app:csvg_withElevation="true">
  // child view
</com.blue.view.ShapeRelativeLayout>
```
ConstraintLayout
```xml
<com.blue.view.ShapeConstraintLayout
    android:layout_width="200dp"
    android:layout_height="120dp"
    app:csvg_cornerPosition="topRight|bottomRight"                                 
    app:csvg_cornerRadius="20dp"
    app:csvg_startColor="#8800bc71"
    app:csvg_endColor="#00bc71"
    app:csvg_orientation="LEFT_RIGHT">
  // child view
</com.blue.view.ShapeConstraintLayout>
```
## License
[Apache-2.0](https://opensource.org/licenses/Apache-2.0)
## Update
2018-10-23 : add java implementation
