
# GORM GraphQL Schema Stitching Demonstration

This sample application demonstrates how to add an API methods to a schema generated with GORM GraphQL 


## Step 1: start the application 

   Run the command: ./gradlew run

## Step 2: open graphiql http://localhost:8080/graphiql

  You'll notice that the custom API method and the generated API methods are all present

## Step 3: call the custom API method

  `
    query{
      myCustomAPI(methodparam:"some value")
    }
  `

For a full description and writeup, see the following article: <link to article>

The demonstration is provided by Accuracy Software

www.accuracysoftware.com
Software Development | Engineering and Design | Cloud Hosting and DevOps
