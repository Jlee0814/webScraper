<template>
  <div class="app-container">
    <el-form :inline="true" align="center">
      <el-form-item label="EventName">
        <el-input v-model="searchObj.eventName" placeholder="EventName"/>
      </el-form-item>
      <el-form-item label="Location">
        <el-input v-model="searchObj.eventLocation" placeholder="EventName"/>
      </el-form-item>

      <el-form-item label="Start Date">
        <el-date-picker
          v-model="searchObj.EventDate"
          placeholder="yyyy-MM-dd"
          value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="fetchData()">search</el-button>
        <el-button type="default" @click="resetData()">clear</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="list" border stripe align="center" >
      <!-- <el-table-column type = "index" width="50"/> -->
      <el-table-column
        label="#"
        width="50">
        <template slot-scope="scope"> <!-- template self define-->
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="eventName" label="Event Name" width="250" />
      <el-table-column prop="eventDate" label="Event Date" width="250" />
      <el-table-column prop="eventLocation" label="Location" width="250" />
      <el-table-column prop="websiteName" label="websiteName" width="260" />
    </el-table>
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 30px 0px 0px;text-align: center;"
      layout="total, sizes, prev, pager, next, jumper"
      @current-change="changeCurrentPage"
      @size-change="changePageSize"
    />
  </div>
</template>

<script>
import eventApi from '@/api/event'
export default {
  data() {
    return {
      list: [], // event list
      total: 0, // total records
      page: 1, // current page
      limit: 10,
      searchObj: {}
    }
  },

  created() {
    this.fetchData()
  },
  methods: {
    // load the event list
    fetchData() {
      eventApi.pageList(this.page, this.limit, this.searchObj).then(response => {
        this.list = response.data.rows
        this.total = response.data.total
      })
    },

    changeCurrentPage(page) {
      this.page = page
      this.fetchData()
    },

    changePageSize(size) {
      this.limit = size
      this.fetchData()
    },

    resetData() {
      this.searchObj = {}
      this.fetchData()
    }
  }
}
</script>

