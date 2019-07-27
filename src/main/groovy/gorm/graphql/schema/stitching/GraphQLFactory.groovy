/************************************************
* Ben Lucchesi
* Accuracy Software, 2019
*
*/

package gorm.graphql.schema.stitching

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLArgument
import graphql.Scalars

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.grails.gorm.graphql.Schema

import org.grails.datastore.mapping.core.Datastore

import javax.inject.Singleton
import javax.inject.Inject

@Factory
class GraphQLFactory {

    @Bean
    @Singleton
    GraphQL graphQL(Datastore datastore) {
     
      // initialize GORM GraphQL schema 
      Schema schema = new Schema( datastore.mappingContext )
      schema.initialize()
      GraphQLSchema gormGraphQLSchema = schema.generate()

      def builder = GraphQLSchema.newSchema( gormGraphQLSchema )

      // transform the queryType and use the builder to add a new fielddefinition
      def transformedQuery = builder.queryType.transform( { b -> 
          b.field(
            GraphQLFieldDefinition.newFieldDefinition()
              .name("myCustomAPI")
              .type( Scalars.GraphQLString )
              .argument( GraphQLArgument.newArgument().name("methodparam").type(Scalars.GraphQLString) )
              .dataFetcher( new MyCustomAPIFetcher() )
            )
        } )

      // assign a new queryType in the builder, then build it
      return GraphQL.newGraphQL( builder.query( transformedQuery ).build() ).build() 

    }
}

