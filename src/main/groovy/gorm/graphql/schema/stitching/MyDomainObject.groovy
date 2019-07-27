
package gorm.graphql.schema.stitching

import grails.gorm.annotation.Entity

@Entity
class MyDomainObject{

  String property1
  Integer property2

  static graphql = true

  static constraints = {
  }

  static mapping = {
  }
}
