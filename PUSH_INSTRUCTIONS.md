# 推送代码到GitHub的说明

## 简单的方法（推荐）

在本地环境中执行以下命令：

```bash
cd /workspace/RuoYi
git push origin main
```

## 如果需要认证

当提示输入用户名和密码时：
- 用户名：您的GitHub用户名（Peterleoo）
- 密码：您的GitHub个人访问令牌（PAT）

## 创建GitHub个人访问令牌的方法

1. 访问：https://github.com/settings/tokens
2. 点击 "Generate new token"
3. 选择 "Generate new token (classic)
4. 设置过期时间：选择 "No expiration"
5. 选择范围：勾选 "repo"
6. 点击 "Generate token"
7. 复制生成的token并保存好

## 如果已经有认证缓存

如果您之前的项目可以成功推送，说明您的凭证可能已经缓存。尝试在本地环境中有缓存。在本地环境中直接推送即可。

## 备选方法

您也可以将代码下载到本地，然后使用GitHub Desktop或其他Git工具来推送。

## 注意事项

- 请确保您对Peterleoo/RuoYi仓库有写入权限
- 请确保您的网络可以访问GitHub
- 如果使用令牌要保存好，不要泄露
