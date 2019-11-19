import Voice from '@/components/Voice/'
import VoiceInfo from '@/components/Audio/content'


export default [  
  {
    path: '/voice/list/:sid',
    name: 'voice',
    components: {
      default: Voice
    }
  },{
    path: '/voice/info/:id',
    name: 'VoiceInfo',
    components: {
      default: VoiceInfo
    }
  }
]