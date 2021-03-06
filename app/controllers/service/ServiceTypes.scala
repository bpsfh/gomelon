package controllers

import play.api.mvc._
import play.api.libs.json._
import models._
import java.util.Date
import com.mongodb.casbah.WriteConcern
import se.radley.plugin.salat._
import se.radley.plugin.salat.Binders._
import play.api.data.Form
import play.api.data.Forms._
import play.api.templates._

object ServiceTypes extends Controller {
  def serviceTypeForm(id: ObjectId = new ObjectId): Form[ServiceType] = Form(
    mapping(
      "id" -> ignored(id),
      "serviceTypeName" -> nonEmptyText
      )(ServiceType.apply)(ServiceType.unapply)   
  )
  
  def serviceTypeMain = Action{
  	  Ok(views.html.service.addServiceType(serviceTypeForm()))
  }
  
  def addServiceType = Action { implicit request =>
    serviceTypeForm().bindFromRequest.fold(
      errors => BadRequest(views.html.service.addServiceType(errors)),
      {
        serviceType =>
          ServiceType.addserviceType(serviceType)
          Ok(Html("<p>添加成功！</p>"))                   
      })
  }
}