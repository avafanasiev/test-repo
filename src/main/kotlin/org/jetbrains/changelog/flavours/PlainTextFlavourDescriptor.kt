// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.jetbrains.changelog.flavours

import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.html.OpenCloseGeneratingProvider
import org.intellij.markdown.parser.LinkMap
import java.net.URI

class PlainTextFlavourDescriptor(private val lineSeparator: String) : GFMFlavourDescriptor() {

    override fun createHtmlGeneratingProviders(linkMap: LinkMap, baseURI: URI?) =
        super.createHtmlGeneratingProviders(linkMap, baseURI) + hashMapOf(
            MarkdownElementTypes.LIST_ITEM to CustomProvider("- "),
            MarkdownTokenTypes.EOL to CustomProvider("", lineSeparator)
        )

    private class CustomProvider(private val openTagName: String = "", private val closeTagName: String = "") :
        OpenCloseGeneratingProvider() {

        override fun openTag(visitor: HtmlGenerator.HtmlGeneratingVisitor, text: String, node: ASTNode) {
            visitor.consumeHtml(openTagName)
        }

        override fun closeTag(visitor: HtmlGenerator.HtmlGeneratingVisitor, text: String, node: ASTNode) {
            visitor.consumeHtml(closeTagName)
        }
    }
}
