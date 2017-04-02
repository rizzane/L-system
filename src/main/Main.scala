package main

import processing.core._
import processing._

class Main extends PApplet {
  
//  variables: A, B
//  axiom: A
//  rules: (A -> AB), (B -> A)¨
  var len = 100f
  
  var axiom = "F"
  
  var sentence = axiom
  
  var rule = {
    Map('F' -> "FF+[+F-F-F]-[-F+F+F]")
  }
  
  def generate() = {
    len *= 0.5f
    var nextSentence = ""
    for(i <- 0 until sentence.length) {
     var current = sentence.charAt(i)     
     nextSentence += {
       current match {
       case 'F' => rule.get(current).get
       case _ => current
       }
     }
    }
    sentence = nextSentence
    grow
  }
  
  def grow() = {
    translate(width/2, height)
    stroke(255)
    for(i <- 0 until sentence.length) {
      var current = sentence.charAt(i)
      current match {
        case 'F' => {
          line(0,0,0,-len)
          translate(0,-len)
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
    size(600,600)
  }
  
  
  override def setup = {
    background(51)
  }
  
  
  override def draw = {
    fill(255)
    rect(30,20,100,50)
  }
  
  override def mousePressed() = {
    if(mouseX > 30 && mouseX < 130 && mouseY > 20 && mouseY < 70 && mousePressed) {      
      generate()
    }
  }
}