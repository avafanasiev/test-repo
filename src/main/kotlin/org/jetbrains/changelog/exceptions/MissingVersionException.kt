// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.jetbrains.changelog.exceptions

class MissingVersionException(version: String?) : Exception("Version is missing: $version")
