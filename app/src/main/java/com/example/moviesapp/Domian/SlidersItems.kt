package com.example.moviesapp.Domian

class SlidersItems(
    var image: Int
) {
    fun getImage(): Int {
        return image
    }
    fun sliderItems(){
        this.image = image
    }
    fun setImage(){
        this.image = image
    }
}