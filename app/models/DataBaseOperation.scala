package models

import slick.dbio.DBIO


trait DataBaseOperation[T] {
  def insert(entity: Entity)
  def update(id: Long, entity: Entity)
  def delete(id: Long)
  def list
  def get(id: Long)
}
