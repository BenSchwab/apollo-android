// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.custom_scalar_type

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.internal.QueryDocumentMinifier
import com.apollographql.apollo.response.ScalarTypeAdapters
import com.apollographql.apollo.response.ScalarTypeAdapters.DEFAULT
import com.example.custom_scalar_type.type.CustomType
import java.io.IOException
import java.util.Date
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.jvm.Throws
import okio.BufferedSource

@Suppress("DEPRECATION", "NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
class TestQuery : Query<TestQuery.Data, TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID
  override fun queryDocument(): String = QUERY_DOCUMENT
  override fun wrapData(data: Data?): Data? = data
  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES
  override fun name(): OperationName = OPERATION_NAME
  override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper {
    Data(it)
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource, scalarTypeAdapters: ScalarTypeAdapters): Response<Data>
      = SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters)

  @Throws(IOException::class)
  override fun parse(source: BufferedSource): Response<Data> = parse(source, DEFAULT)

  data class Hero(
    val __typename: String = "Character",
    /**
     * The name of the character
     */
    val name: String,
    /**
     * The date character was born.
     */
    val birthDate: Date,
    /**
     * The dates of appearances
     */
    val appearanceDates: List<Date>,
    /**
     * The date character was born.
     */
    val fieldWithUnsupportedType: Any,
    /**
     * Profile link
     */
    val profileLink: java.lang.String,
    /**
     * Links
     */
    val links: List<java.lang.String>
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller { writer ->
      writer.writeString(RESPONSE_FIELDS[0], this@Hero.__typename)
      writer.writeString(RESPONSE_FIELDS[1], this@Hero.name)
      writer.writeCustom(RESPONSE_FIELDS[2] as ResponseField.CustomTypeField, this@Hero.birthDate)
      writer.writeList(RESPONSE_FIELDS[3], this@Hero.appearanceDates) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeCustom(CustomType.DATE, value)}
      }
      writer.writeCustom(RESPONSE_FIELDS[4] as ResponseField.CustomTypeField,
          this@Hero.fieldWithUnsupportedType)
      writer.writeCustom(RESPONSE_FIELDS[5] as ResponseField.CustomTypeField, this@Hero.profileLink)
      writer.writeList(RESPONSE_FIELDS[6], this@Hero.links) { value, listItemWriter ->
        value?.forEach { value ->
          listItemWriter.writeCustom(CustomType.URL, value)}
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("name", "name", null, false, null),
          ResponseField.forCustomType("birthDate", "birthDate", null, false, CustomType.DATE, null),
          ResponseField.forList("appearanceDates", "appearanceDates", null, false, null),
          ResponseField.forCustomType("fieldWithUnsupportedType", "fieldWithUnsupportedType", null,
              false, CustomType.UNSUPPORTEDTYPE, null),
          ResponseField.forCustomType("profileLink", "profileLink", null, false, CustomType.URL,
              null),
          ResponseField.forList("links", "links", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): Hero = reader.run {
        val __typename = readString(RESPONSE_FIELDS[0])
        val name = readString(RESPONSE_FIELDS[1])
        val birthDate = readCustomType<Date>(RESPONSE_FIELDS[2] as ResponseField.CustomTypeField)
        val appearanceDates = readList<Date>(RESPONSE_FIELDS[3]) { reader ->
          reader.readCustomType<Date>(CustomType.DATE)}
        val fieldWithUnsupportedType = readCustomType<Any>(RESPONSE_FIELDS[4] as
            ResponseField.CustomTypeField)
        val profileLink = readCustomType<java.lang.String>(RESPONSE_FIELDS[5] as
            ResponseField.CustomTypeField)
        val links = readList<java.lang.String>(RESPONSE_FIELDS[6]) { reader ->
          reader.readCustomType<java.lang.String>(CustomType.URL)}
        Hero(
          __typename = __typename,
          name = name,
          birthDate = birthDate,
          appearanceDates = appearanceDates,
          fieldWithUnsupportedType = fieldWithUnsupportedType,
          profileLink = profileLink,
          links = links
        )
      }
    }
  }

  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller { writer ->
      writer.writeObject(RESPONSE_FIELDS[0], this@Data.hero?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forObject("hero", "hero", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): Data = reader.run {
        val hero = readObject<Hero>(RESPONSE_FIELDS[0]) { reader ->
          Hero(reader)
        }
        Data(
          hero = hero
        )
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "5b1986dc0a04871a5bcbfdb1d7a5b9f935fd9b2d68da8990744106c4b253f177"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  hero {
          |    __typename
          |    name
          |    birthDate
          |    appearanceDates
          |    fieldWithUnsupportedType
          |    profileLink
          |    links
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
  }
}
