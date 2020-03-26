// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_used_twice.fragment

import com.apollographql.apollo.api.GraphqlFragment
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.fragment_used_twice.type.CustomType
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("DEPRECATION", "NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter")
data class CharacterDetails(
  val __typename: String = "Character",
  /**
   * The name of the character
   */
  val name: String,
  /**
   * The date character was born.
   */
  val birthDate: Any
) : GraphqlFragment {
  override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller { writer ->
    writer.writeString(RESPONSE_FIELDS[0], this@CharacterDetails.__typename)
    writer.writeString(RESPONSE_FIELDS[1], this@CharacterDetails.name)
    writer.writeCustom(RESPONSE_FIELDS[2] as ResponseField.CustomTypeField,
        this@CharacterDetails.birthDate)
  }

  companion object {
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forCustomType("birthDate", "birthDate", null, false, CustomType.DATE, null)
        )

    val FRAGMENT_DEFINITION: String = """
        |fragment CharacterDetails on Character {
        |  __typename
        |  name
        |  birthDate
        |}
        """.trimMargin()

    operator fun invoke(reader: ResponseReader): CharacterDetails = reader.run {
      val __typename = readString(RESPONSE_FIELDS[0])
      val name = readString(RESPONSE_FIELDS[1])
      val birthDate = readCustomType<Any>(RESPONSE_FIELDS[2] as ResponseField.CustomTypeField)
      CharacterDetails(
        __typename = __typename,
        name = name,
        birthDate = birthDate
      )
    }
  }
}
