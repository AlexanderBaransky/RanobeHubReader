/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */
package com.sanyapilot.ranobehubreader.API.models

data class GeneralInfoModel (

    val data : Data
)

data class Data (

    val id : Int,
    val names : Names,
    val rating : Int,
    val year : Int,
    val synopsis : String,
    val url : String,
    val posters : Posters,
    val isSpecial : Boolean,
    val liked : Boolean,
    val authors : List<Authors>,
    val translators : List<Translators>,
    val description : String,
    val status : GeneralInfoStatus,
    val start_reading_url : String,
    val html : String,
    val tags : Tags
)

data class Names (

    val eng : String,
    val rus : String
)

data class Posters (

    val big : String,
    val medium : String,
    val small : String,
    val tiny : String,
    val color : String
)

data class Pivot (

    val ranobe_id : Int,
    val translator_id : Int
)

data class Authors (

    val name_eng : String,
    val pivot : Pivot
)

data class Translators (

    val name : String,
    val pivot : Pivot
)

data class GeneralInfoStatus (

    val id : Int,
    val title : String
)

data class Tags (

    val events : List<Events>,
    val genres : List<Genres>
)

data class Events (

    val id : Int,
    val names : Names,
    val url : String,
    val title : String,
    val description : String
)

data class Genres (

    val id : Int,
    val names : Names,
    val url : String,
    val title : String,
    val description : String
)