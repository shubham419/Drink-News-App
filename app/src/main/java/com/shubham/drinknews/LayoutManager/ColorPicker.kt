package com.shubham.drinknews.LayoutManager

object ColorPicker {
       val color = arrayOf("#556f43","#50bdb7","#31de75","#66b496","#916538","#378c25","#2e914f","#5638cf","#39689e","#5397f8","#21fc32"
       ,"#54751e","#14e5a9")

       var colorIndex = 1
    fun getcolor():String{
        return color[colorIndex++ % color.size]
    }

}