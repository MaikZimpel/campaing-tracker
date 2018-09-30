<template>
  <div>
  <div id="ct-input">
    <form @submit.prevent="handleSubmit">
      <p class="in">Input the alliances, start and end date and click OK</p>
      <div class="input-box">
        <div class="left-div">
          <label for="a1-in" id="a1-lbl" class="in">Alliance 1</label><input class="in" id="a1-in" v-model="campaign.allianceOne" required><br/>
          <label class="in" for="a2-in" id="a2-lbl">Alliance 2</label><input class="in" id="a2-in" v-model="campaign.allianceTwo" required>
        </div>
        <div class="right-div">
          <datepicker class="in" id="sd-dp" placeholder="Start date" wrapper-class="out" v-model="campaign.startDate"></datepicker>
          <datepicker class="in" id="ed-dp" placeholder="End date" wrapper-class="out" v-model="campaign.endDate"></datepicker>
        </div>
      </div>
      <button class="in out">OK</button>
    </form>
  </div>
  <div v-if="campaign.allianceOne.length > 0 && campaign.allianceTwo.length > 0">
    <p>{{campaign.allianceOne}} vs {{campaign.allianceTwo}} between {{campaign.startDate}} and {{campaign.endDate}}</p>
  </div>
  </div>
</template>

<script>
import Datepicker from 'vuejs-datepicker'

export default {
  name: 'c-input',
  components: {
    Datepicker
  },
  data () {
    return {
      campaign: {
        allianceOne: '',
        allianceTwo: '',
        startDate: '',
        endDate: ''
      }
    }
  },
  methods: {
    handleSubmit () {
      fetch('http://localhost:8080/' + this.campaign.allianceOne + '/' + this.campaign.allianceTwo + '/' + this.campaign.startDate + '/' + this.campaign.endDate)
        .then(response => response.json())
        .then((data) => {
          // TODO
        })
    }
  }
}
</script>

<style scoped>
  .in {
    margin: 5px;
    font-size: 13px;
  }
  .out {
    padding-top: 1px;
    padding-bottom: 1px;
  }
  .left-div {
    float: left;
    width: 300px;
  }
  .right-div {
    margin-left: 308px;
    width: 250px;
  }
  .input-box {
    border-color: #440044;
    border-style: solid;
    width: min-content;
    align-content: flex-start;
    margin: auto;
  }

</style>
