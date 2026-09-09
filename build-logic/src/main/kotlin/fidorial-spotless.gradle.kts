plugins {
    id("com.diffplug.spotless")
}

spotless {
    ratchetFrom("origin/HEAD")

    format("misc") {
        target(
            "*.gradle.kts",
            "*.md",
            "*.properties",
            "*.json",
            "*.toml",
            "*.xml",
            "*.yml",
            "*.yaml",
        )
        targetExclude(
            ".gradle/**",
            ".idea/**",
            "**/build/**",
            "**/run/**",
            "**/patches/**",
        )
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        ktlint("1.8.0")
    }

    kotlinGradle {
        ktlint("1.8.0")
    }

    java {
        target("**/*.java")
        targetExclude(
            "**/build/**",
            "**/generated/**",
            "**/patches-workspace/**",
        )

        //palantirJavaFormat("2.96.0")
        forbidWildcardImports()
        formatAnnotations()
        removeUnusedImports()
        //custom("Wrap declaration parameters", WrapDeclarationParameters())
        replaceRegex("Expand empty code blocks", """(?m)^([ \t]*)(.+) \{\}$""", "$1$2 {\n$1}")
        replaceRegex(
            "Separate module requires static",
            """(?m)^(    requires (?!static )[^\n]+;\n)(    requires static )""",
            "$1\n$2",
        )

        importOrder("", "javax|java", "\\#")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
