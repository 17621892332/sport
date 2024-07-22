<template>
	<view>
		<!-- <view class="container">
			<view class="list-cell b-b m-t" @click="inputShowModal('nickname')" hover-class="cell-hover" :hover-stay-time="50">
				<text class="cell-tit">修改昵称</text>
				<text class="cell-more yticon icon-you"></text>
			</view>
			<view class="list-cell b-b" @click="genderShowModal" hover-class="cell-hover" :hover-stay-time="50">
				<text class="cell-tit">修改性别</text>
				<text class="cell-more yticon icon-you"></text>
			</view>

			<neil-modal :show="inputShow" @close="cancel" title="编辑" @cancel="cancel" @confirm="confirm">
				<input v-model="inputContent" style="margin:20upx" placeholder="请输入..." />
			</neil-modal>

			<neil-modal :show="genderShow" @close="cancel" title="选择性别" @cancel="cancel" @confirm="confirmGender">
				<view>
					<radio-group style="text-align:center" @change="genderRadioChange">
						<label v-for="(item, index) in genders" :key="item.value">
							<radio :value="item.value + ''" :checked="index === gender" style="margin:10upx" />{{item.name}}
						</label>
					</radio-group>
				</view>
			</neil-modal>
		</view> -->
		<view style="height: 120rpx;"
		class="solid-top bg-white flex justify-between align-center margin-bottom-sm padding-lr padding-tb-s">
			<view class="title text-black">头像</view>
			<image @click="chooseImg" :src="userInfo.avatarUrl ? userInfo.avatarUrl :'../../static/user/touxiang-@2x.png'"
			class="round" mode="aspectFill"
			style="width: 90rpx;height: 90rpx;"></image>
		</view>
		<view style="height: 100rpx;"
		class="solid-bottom bg-white flex justify-between align-center padding-lr padding-tb-s">
			<view class="title text-black">昵称</view>
			<view class="flex align-center">
				<!-- <view class="nick-name">
					{{ userInfo.nickname ?userInfo.nickname : '未设置昵称' }}
				</view> -->
				<input @blur="changeName" @confirm="changeName"
				v-model="userInfo.nickname" placeholder="请输入昵称" class="text-df" style="text-align: right;" />
				<!-- <image src="../../static/icon/arrow_right.png" mode="aspectFit"
				class="icon-right"></image> -->
			</view>
		</view>
		<view style="height: 100rpx;"
		class="solid-bottom bg-white flex justify-between align-center padding-lr padding-tb-s">
			<view class="title text-black">性别</view>
			<view class="flex align-center">
				<view @click="changeGender(1)" class="flex align-center">
					<image
					:src="userInfo.gender == 1 ?'/static/cart/selected.png':'/static/cart/select.png'"
					mode="aspectFill"
					style="width: 40rpx;height: 40rpx;"></image>
					<view style="font-size: 28rpx;line-height: 38rpx;color: #5E5E66;padding-left: 20rpx;">男</view>
				</view>
				<view @click="changeGender(2)" class="flex align-center" style="padding-left: 80rpx;">
					<image
					 :src="userInfo.gender == 2 ?'/static/cart/selected.png':'/static/cart/select.png'"
					 mode="aspectFit"
					style="width: 40rpx;height: 40rpx;"></image>
					<view style="font-size: 28rpx;line-height: 38rpx;color: #5E5E66;padding-left: 20rpx;">女</view>
				</view>
			</view>
		</view>
		<view style="height: 100rpx;"
		class="solid-bottom bg-white flex justify-between align-center padding-lr padding-tb-s">
			<view class="title text-black">生日</view>
			<view class="flex align-center">
				<picker mode="date" @change="bindDateChange">
					<view class="lem-text-grey content">{{date}}</view>
				</picker>
				<!-- <view class="lem-text-grey content">2000年10月09日</view> -->
				<image src="../../static/icon/arrow_right.png" mode="aspectFit"
				class="icon-right"></image>
			</view>
		</view>
		<view style="height: 100rpx;"
		class="solid-bottom bg-white flex justify-between align-center padding-lr padding-tb-s">
			<view class="title text-black">羽毛球等级</view>
			<view class="level-info" @click="showLevelInfoModal">(等级参考标准)</view>
			<view class="flex align-center">
				 <picker mode="selector" :range="levels" @change="bindLevelChange">
					<view class="lem-text-grey content">{{selectedLevel}}</view>
				  </picker>
				<image src="../../static/icon/arrow_right.png" mode="aspectFit"
				class="icon-right"></image>
			</view>
		</view>
		<view style="height: 100rpx;"
		class=" bg-white flex justify-between align-center padding-lr padding-tb-s">
			<view class="title text-black">手机号</view>
			<view class="lem-text-grey content" style="padding-right: 34rpx;">{{phone}}</view>
		</view>
		<show-modal></show-modal>
		<!-- 添加 modal 组件 -->
		<neil-modal :show="showLevelInfo" @close="cancel" title="等级参考标准">
		  <!-- 这里放置羽毛球等级的参考标准内容 -->
		  <view style="padding: 20px;">
			<text style="font-size: 16px; font-weight: bold; margin-bottom: 10px;">
			  特别说明，以下为业余球友等级：
			</text>
			<view style="margin-bottom: 10px;">
			  <text style="color: orange; font-weight: bold;">1级：</text>
			  <text>初学者，能把球打过网</text>
			</view>
			<view style="margin-bottom: 10px;">
			  <text style="color: orange; font-weight: bold;">2级：</text>
			  <text>了解基本规则和基本技术，但很不稳定</text>
			</view>
			<view style="margin-bottom: 10px;">
			  <text style="color: orange; font-weight: bold;">3级：</text>
			  <text>熟悉基本技术，发力还未掌握好，不会反手，会基础步法但无法用于实战</text>
			</view>
			<view style="margin-bottom: 10px;">
			  <text style="color: orange; font-weight: bold;">4级：</text>
			  <text>基本技术熟练运用，实战中能简单应用反手，并正确使用步法</text>
			</view>
			<view style="margin-bottom: 10px;">
			  <text style="color: orange; font-weight: bold;">5级：</text>
			  <text>技术比较全面，击球到位，有简单战术应用</text>
			</view>
			<view style="margin-bottom: 10px;">
			  <text style="color: orange; font-weight: bold;">6级：</text>
			  <text>技术全面，步法娴熟，战术比较丰富</text>
			</view>
			<view style="margin-bottom: 10px;">
			  <text style="color: orange; font-weight: bold;">7级：</text>
			  <text>很强的攻防能力，熟练应用各种高级技战术，能及时调整战术和心态</text>
			</view>
			<view style="margin-bottom: 10px;">
			  <text style="color: orange; font-weight: bold;">8级：</text>
			  <text>经常取得大型业余比赛前三名，能和专业退役运动员对抗</text>
			</view>
			<view style="margin-bottom: 10px;">
			  <text style="color: orange; font-weight: bold;">9级：</text>
			  <text>专业退役运动员</text>
			</view>
			<view>
			  <text style="color: orange; font-weight: bold;">10级：</text>
			  <text>现役专业运动员</text>
			</view>
		  </view>
		</neil-modal>
	</view>
</template>

<script>
	import {
		mapState,
		mapMutations
	} from 'vuex';
	export default {

		data() {
			return {
				levels: ['1级', '2级', '3级', '4级', '5级', '6级', '7级', '8级', '9级', '10级'],
				date:'选择您的出生日期',
				phone:'',
				showLevelInfo: false,  // 控制等级参考标准 modal 的显示
				selectedLevel: '请选择等级'
			};
		},
		computed: {
			...mapState(['userInfo']),
		},
		onLoad() {
			if(this.userInfo.birthday){
				var date = new Date(this.userInfo.birthday)
				var month = date.getMonth() >= 9 ? date.getMonth()+1 : '0'+parseInt(date.getMonth()+1)
				var day = date.getDate() >= 9 ? date.getDate() : '0'+parseInt(date.getDate())
				this.date = date.getFullYear()+'-'+month+'-'+day
			}
			this.phone = this.userInfo.phone[0]+this.userInfo.phone[1]+this.userInfo.phone[2]+'****'+this.userInfo.phone[7]+this.userInfo.phone[8]+this.userInfo.phone[9]+this.userInfo.phone[10]
		},
		methods: {
			chooseImg(){
				this.$api.uploadImg(1,(res)=>{
					console.log(res)
					this.userInfo.avatarUrl = res
					var info = this.userInfo
					var tempObj = Object.assign({},this.userInfo,info)
					this.userInfo = this.login(tempObj)
					this.syncUser()
					//uni.setStorageSync('userInfo',this.userInfo)
				})
			},
			changeGender(index){
				this.userInfo.gender = index
				this.syncUser()
			},
			changeName(){
				this.syncUser()
			},
			syncUser(){
				this.$api.request('post', 'user/app/updateUser', this.userInfo).then(syncRes => {
					//同步过后
					uni.setStorageSync('userInfo', this.userInfo)
				})
			},
			  bindLevelChange: function(e) {
				    const selectedIndex = e.target.value; // 获取选择的索引
				    const selectedLevel = this.levels[selectedIndex]; // 根据索引获取选择的等级值
				    this.userInfo.levels = selectedLevel; // 更新用户信息中的等级字段
				    // 更新页面中的选择显示
					this.selectedLevel=selectedLevel
				  this.syncUser()
			  },
			 bindDateChange: function(e) {
				 console.log(e)
				this.userInfo.birthday = new Date(e.target.value).getTime()
				console.log(this.userInfo.birthday)
				this.date = e.target.value
				this.syncUser()
			},
			  showLevelInfoModal() {
				this.showLevelInfo = true; // 打开等级参考标准 modal
			  },
			  cancel() {
				this.showLevelInfo = false; // 关闭 modal 的方法
			  },
			...mapMutations(['login'])
		},
	}
</script>

<style lang="scss">
	page {
		background: $page-color-base;
	}

	.solid-top{
		border-top: #F2F2F2 solid 2rpx;
	}

	.solid-bottom{
		border-bottom: #F2F2F2 solid 2rpx;
	}

	.padding-tb-s{
		padding: 15rpx 30rpx;
	}

	.title{
		font-size: 32rpx;
		line-height: 38rpx;
	}

	.content{
		font-size: 28rpx;
		line-height: 38rpx;
	}

	.nick-name{
		color: #2AAB34;
		font-size: 28rpx;
		line-height: 38rpx;
	}

	.icon-right{
		width: 14rpx;
		height: 27rpx;
		margin-left: 18rpx;
	}
	  .level-info {
	    font-size: 28rpx;
	    color: #007AFF;
	    margin-left: 10rpx;
	    cursor: pointer;
	  }
</style>
