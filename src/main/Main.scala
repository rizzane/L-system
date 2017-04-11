package main

import processing.core._
import processing._

class Main extends PApplet {
  
  var length = 100f
  
  var axiom = "X"
  
  var sentence = axiom
  
  val rule1 = {
    Map('F' -> "FF+[+F-F-F]-[-F+F+F]")
  }
  
  val rule2 = Map('X' → "Y−[[X]+X]+Y[+YX]−X", 'Y' → "YY")
  
  def generate() = {
    length *= 0.6f
    var nextSentence = ""
    for(i <- 0 until sentence.length) {
     var current = sentence.charAt(i)     
     nextSentence += {
       current match {
       case 'F' => rule1.get(current).get
       case 'Y' => rule2.get(current).get
       case 'X' => rule2.get(current).get
       case _ => current
       }
     }
    }
    sentence = nextSentence
    grow
  }
  
  def grow() = {
    translate(250, height)
    stroke(255)
    for(i <- 0 until sentence.length) {
      var current = sentence.charAt(i)
      current match {
        case 'F' => {
          line(0,0,0,-length)
          translate(0,-length)
        }
        case 'Y' => {
          line(0,0,0,-length)
          translate(0,-length)          
        }
        case '+' => rotate(Math.PI.toFloat/7.2f)
        case '-' => rotate(-Math.PI.toFloat/7.2f)
        case '[' => pushMatrix
        case ']' => popMatrix
        case _ => 
      }
    }
  }
  
  override def settings = {
    size(900,900)
  }
  
  
  override def setup = {
    background(0)
  }
  
  
  override def draw = {
    fill(255)
    rect(30,20,100,50)
    rect(150, 20, 100, 50)
    //Grow
    textSize(20)
    fill(0, 102, 153)
    text("Grow", 40, 55)
    //Change
    text("Change", 160, 55)
  }
  
  override def mousePressed() = {
    if(mouseX > 30 && mouseX < 130 && mouseY > 20 && mouseY < 70) {      
      generate()
    } else if(mouseX > 150 && mouseX < 250 && mouseY > 20 && mouseY < 70) {
      background(0)
      if(axiom == "X") {
        axiom = "F"
      } else {
        axiom = "X"
      }
      sentence = axiom
      length = 100
    }
  }
}