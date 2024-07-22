<template>
	<view class="container">
		<view class="form">
			<view class="form-item">
				<text class="label">名称：</text>
				<input type="text" placeholder="请输入活动名称" v-model="form.name" />
			</view>
			<view class="form-item">
				<text class="label">日期：</text>
				<picker mode="date" @change="handleDateChange">
					<view class="picker">
						<text>{{ form.date || '请选择日期' }}</text>
					</view>
				</picker>
			</view>
			<view class="form-item">
				<text class="label">时间：</text>
				<picker mode="time" @change="handleTimeChange">
					<view class="picker">
						<text>{{ form.time || '请选择时间' }}</text>
					</view>
				</picker>
			</view>
			<view class="form-item">
				<text class="label">地点：</text>
				<input type="text" placeholder="请选择地址" v-model="form.location" />
			</view>
			<view class="form-item">
				<text class="label">场地号：</text>
				<input type="text" placeholder="待定先不填" v-model="form.fieldNumber" />
			</view>
			<view class="form-item">
				<text class="label">人数：</text>
				<text class="text">预计{{ form.participants }}人</text>
			</view>
			<view class="form-item">
				<text class="label">报名费：</text>
				<text class="text">活动后按人收取</text>
			</view>
			<view class="form-item">
				<text class="label">金额(元)：</text>
				<view class="amount-input">
					<input type="number" placeholder="男" v-model="form.maleFee" />
					<input type="number" placeholder="女" v-model="form.femaleFee" />
				</view>
			</view>
			<view class="form-item">
				<text class="label">限报名：</text>
				<text class="text">不限等级</text>
			</view>
			<view class="form-item">
				<text class="label">退坑：</text>
				<text class="text">提前2小时</text>
			</view>
			<view class="form-item">
				<text class="label">带人：</text>
				<text class="text">可带人(需付报名费)，不限人数</text>
			</view>
			<view class="form-item">
				<text class="label">补报名：</text>
				<text class="text">开始后，不可补报名</text>
			</view>
			<view class="form-item">
				<text class="label">补充：</text>
				<textarea placeholder="请输入补充信息" v-model="form.additionalInfo" />
			</view>
		</view>
		<view class="form-buttons">
			<button class="button clear-button" @click="handleClear">清空</button>
			<button class="button submit-button" @click="handleSubmit">发布</button>
			<button class="button save-button" @click="handleSave">保存</button>
		</view>
	</view>
</template>


<script>
	export default {
		data() {
			return {
				form: {
					name: '',
					date: '',
					time: '',
					location: '',
					fieldNumber: '',
					participants: 16,
					maleFee: '',
					femaleFee: '',
					additionalInfo: '',
				},
			};
		},
		methods: {
			handleDateChange(e) {
				this.form.date = e.target.value;
			},
			handleTimeChange(e) {
				this.form.time = e.target.value;
			},
			handleClear() {
				this.form = {
					name: '',
					date: '',
					time: '',
					location: '',
					fieldNumber: '',
					participants: 16,
					maleFee: '',
					femaleFee: '',
					additionalInfo: '',
				};
			},
			handleSubmit() {
				const that = this
				if (that.submiting) {
					return
				}
				uni.showLoading({})
				that.submiting = true
				that.$api.request('post', 'order/app/takeOrder',
					JSON.stringify(that.form), failres => {
						that.submiting = false
						that.$api.msg(failres.msg)
					}).then(res => {
					//提交订单成功后，无需再让用户提交订单
				})
				console.log('提交表单', this.form);
			},
			handleSave() {
				// 保存表单逻辑
				console.log('保存表单', this.form);
			},
		},
	};
</script>

<style>
	.container {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		height: 100vh;
		padding: 20rpx;
	}

	.form {
		flex: 1;
		display: flex;
		flex-direction: column;
		padding: 20rpx;
	}

	.form-item {
		display: flex;
		align-items: center;
		padding: 15rpx 0;
		border-bottom: 1rpx solid #e0e0e0;
	}

	.label {
		width: 180rpx;
		font-size: 28rpx;
		color: #888;
	}

	.text {
		font-size: 28rpx;
		color: #333;
	}

	.picker {
		flex: 1;
		padding: 10rpx;
		border: 1rpx solid #ccc;
		border-radius: 8rpx;
		color: #333;
	}

	input {
		flex: 1;
		padding: 10rpx;
		border: 1rpx solid #ccc;
		border-radius: 8rpx;
		font-size: 28rpx;
		color: #333;
	}

	.amount-input {
		display: flex;
	}

	.amount-input input {
		flex: 1;
		margin-right: 10rpx;
		font-size: 28rpx;
	}

	textarea {
		width: 100%;
		height: 100rpx;
		border: 1rpx solid #ccc;
		border-radius: 8rpx;
		padding: 10rpx;
		font-size: 28rpx;
		color: #333;
	}

	.form-buttons {
		display: flex;
		justify-content: space-between;
		padding: 20rpx 0;
	}

	.button {
		flex: 1;
		margin: 0 10rpx;
		padding: 20rpx 0;
		text-align: center;
		border: none;
		border-radius: 8rpx;
		color: white;
		font-size: 28rpx;
	}

	.clear-button {
		background-color: #e0e0e0;
		color: #333;
	}

	.submit-button {
		background-color: #ff5722;
	}

	.save-button {
		background-color: #4caf50;
	}
</style>