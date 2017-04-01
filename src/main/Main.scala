package main

import processing.core._

class Main extends PApplet {
  
  
  override def settings = {
    size(600,600)
  }
  
  
  override def setup = {
    background(0)
  }
  
  
  override def draw = {
    fill(255)
    ellipse(50,50,50,50)
  }
  
}