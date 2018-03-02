/**
  * Created by Hessam Shafiei Moqaddam on 2/26/18.
  */

import java.awt.image.BufferedImage

import scala.swing.{Dimension, Graphics2D, Panel}

class MandelbrotDrawer extends Panel{

  var scale: Array[Double] = Array(28,28)
  var position: Array[Double] = Array(0,0)

  def getPosition = {
    this.position
  }
  def setPosition(p: Array[Double]): Unit = {
    this.position = p
  }

  def getScale = {
    this.scale
  }
  def setScale(s: Array[Double]): Unit = {
    this.scale = s
  }

  override def paint(g: Graphics2D): Unit = {

    super.paint(g)

    val d: Dimension = getSize()
    var x: Int
    var y: Int
    var iteration: Int
    var real: Double
    var imaginary: Double
    var cr, ci: Double
    var tr: Double = 0
    var ti: Double = 0
    var mod: Double = 0
    var r,g,b: Int
    var mu: Float
    val image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB)

    for(x <- 0 to d.width){
      for(y <- 0 to d.height){
        cr = (x - position(0)).toDouble / scale(0)
        ci = -(y - position(1)).toDouble / scale(1)
        real = 0
        imaginary = 0
        for(iteration <- 0 to MandelbrotDrawer.maxItr){
          tr = real * real - imaginary * imaginary + cr
          ti = 2 * real * imaginary + ci
          real = tr
          imaginary = ti
          mod = real * real + imaginary * imaginary
          //TODO: implement break
          //if(mod > 4)
          //break;
        }
        mu = (iteration - (Math.log(Math.log(Math.sqrt(mod)))) / Math.log(2.0)).toFloat
        mu *= 10
        image.setRGB(x,y,mu.toInt)
      }
    }
    g.drawImage(image,0,0,this) //TODO: why scala.Graphics2D has no drawImage method
  }

}

object MandelbrotDrawer{

  private val maxItr: Int = 180

}
