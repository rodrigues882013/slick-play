package models

import slick.jdbc.H2Profile.api._

case class Entity (id: Long,
                   name: String,
                   description: Option[String],
                   created: String,
                   lastUpdated: String)


class Entities(tag: Tag) extends Table[Entity](tag, "entity") with DataBaseOperation[Entity]{

  val entities = TableQuery[Entities]

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def description = column[Option[String]]("description")
  def created = column[String]("created")
  def lastUpdated = column[String]("last_updated")
//  override def * = (id, name, description, created, lastUpdated).mapTo[Entity]
  def * = (id, name, description, created, lastUpdated) <> (Entity.tupled, Entity.unapply)


  override def insert(entity: Entity) = entities ++= Seq(entity)

  override def update(id: Long, entity: Entity) =
    entities
      .filter(_.id === id)
      .map(_ => Map(name -> entity.name, description -> entity.description))

  override def delete(id: Long) = entities.filterNot(_.id === id)

  override def list = entities.filter(_.id > 0)

  override def get(id: Long) = entities.filter(_.id === id)
}