package io.aikosoft.estore.data.repositories.implementations

import io.aikosoft.estore.data.network.SampleClient
import io.aikosoft.estore.data.repositories.SampleRepository

class SampleRepositoryImpl(private val sampleClient: SampleClient) : SampleRepository